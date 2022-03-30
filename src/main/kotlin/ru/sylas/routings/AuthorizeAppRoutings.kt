package ru.sylas.routings

import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.http.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Tag
import ru.sylas.common.myApiRouting
import ru.sylas.exceptions.HellException
import ru.sylas.exceptions.UnknownAppID
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile
import ru.sylas.service.appservice.AppService

fun Application.appRouting(){
    val service:AppService by inject()
    myApiRouting {
        tag(Tag.App){
                route("/app") {
                    throws(
                    status = HttpStatusCode.InternalServerError.description("Проблемы при регистрации"),
                    gen = { e: HellException -> return@throws e.localizedMessage }
                    ){
                    post<Unit,String,NewApp>(
                        info(
                            summary = "Регистрация приложения"
                        ),
                        exampleRequest = NewApp("com.example.myapplication","Competitor-1")
                    ){ _,app->
                      service.regApp(app)
                      respond("OK")
                    }
                    }
                    get<Competitor , List<NewApp>>(
                        info(
                            summary = "Получение списка приложений участника"
                        ),
                        example = listOf(NewApp("com.example.myapplication","Competitor-1"))
                    ) {competitor->

                        respond(service.getApps(competitor))
                    }

                }
            route("/mobile") {
                throws(
                status = HttpStatusCode.Unauthorized.description("Отсутствует приложение с таким appId"),
                gen = { _:UnknownAppID -> return@throws "Отсутствует приложение с таким appId" }
            ).
                throws(
                    status = HttpStatusCode.InternalServerError.description("Проблемы при регистрации"),
                    gen = { e: HellException -> return@throws e.localizedMessage }
                ){
                    post<Unit, KeyDevice,NewMobile>(
                        info(
                            summary = "Регистрация устройства"
                        ),
                        exampleRequest = NewMobile("qwerdsa134ed","com.example.myapplication","Xiaomi Mi A3"),
                        exampleResponse = KeyDevice("d4961ca54809b3bc254709d04bb82c1b1684336826d3a7d89f7ff917a20a3de5")
                    ){ _,mobile->
                        respond(service.regMobile(mobile))

                    }
                }
                get<AppId , List<NewMobile>>(
                    info(
                        summary = "Получение списка устройств участника"
                    ),
                    example = listOf(NewMobile("qwerdsa134ed","com.example.myapplication","Xiaomi Mi A3"))
                ) {appId->
                    respond(service.getMobile(appId))
                }
            }

        }
    }
}