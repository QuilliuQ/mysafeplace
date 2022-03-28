package ru.sylas.routings

import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import com.papsign.ktor.openapigen.route.tag
import com.papsign.ktor.openapigen.route.throws
import io.ktor.application.*
import io.ktor.http.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Tag
import ru.sylas.common.myApiRouting
import ru.sylas.exceptions.HellException
import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.HeaderKeyDevice
import ru.sylas.model.requestdataclasses.NewTV
import ru.sylas.model.requestdataclasses.toKeyDevice
import ru.sylas.service.tvservice.TVService


fun Application.configureTVRouting(){

    val service:TVService by inject()

    myApiRouting {
        tag(Tag.TV){
            throws(
                status = HttpStatusCode.InternalServerError.description("Проблемы при регистрации"),
                gen = { e: HellException -> return@throws e.localizedMessage }
            ){
            route("/tv"){
                route("/app"){
                    post<Unit, KeyDevice, NewTV>(
                        info(
                            summary = "Регистрация устройства"
                        ),
                        exampleRequest = NewTV("qwerdsa134ed","Vasya","Xiaomi Mi A3")
                    ){ _,tv->
                        respond(service.regApp(tv))
                    }
                }
                route("/game"){
                    get<Unit,Movie>(
                        info("Получение игры"),
                        example = Movie("video.mp4", listOf(
                            Sector(
                            "stage1.mp4",
                            listOf(Option("",true))
                        )
                        ))
                    ){
                        respond(service.getMovie())
                    }
                }
                route("/statistic"){
                    post<HeaderKeyDevice,Unit, TVStats>(
                        info(
                            "Оптравка результата в статистику"
                        ),
                        exampleRequest = TVStats("Vasya",0,129)
                    ){key,stat->
                        respond(service.sendStat(key.toKeyDevice(),stat))
                    }
                }
            }
            }
        }
    }
}