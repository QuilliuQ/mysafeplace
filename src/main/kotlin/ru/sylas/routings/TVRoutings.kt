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
import org.koin.ktor.ext.get
import org.koin.ktor.ext.inject
import ru.sylas.common.Tag
import ru.sylas.common.myApiRouting
import ru.sylas.exceptions.BadKeyDeviceException
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

            route("/tv"){
                throws(
                    status = HttpStatusCode.InternalServerError.description("Проблемы при регистрации"),
                    gen = { e: HellException -> return@throws e.localizedMessage }
                ).route("/app"){
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
                            listOf(Option("frog",true))
                        )
                        ))
                    ){
                        respond(getMovie())
                    }
                }
                route("/statistic"){
                    throws(
                        status = HttpStatusCode.BadRequest.description("KeyDevice не зарегистрирован"),
                        example = ErrorEx("KeyDevice не зарегистрирован"),
                        BadKeyDeviceException::class
                    ).post<HeaderKeyDevice,ErrorEx, TVStats>(
                        info(
                            "Оптравка результата в статистику"
                        ),
                        exampleRequest = TVStats("Vasya",0,129)
                    ){key,stat->
                        service.sendStat(key.toKeyDevice(),stat)
                        respond(ErrorEx("OK"))
                    }
                    get<Unit,List<TVStats>>(
                        info("Получение статистики")
                    , example = listOf(TVStats("Vasya",0,129))
                    ) {
                        respond(service.getStat())
                    }
                }
            }
            }
        }
    }


fun getMovie(): Movie {

    val sector = listOf(

        Sector(
            source = "/video/firstCut.mp4",
            options = listOf(

                Option(
                    source = "dog",
                    assert =false
                ),
                Option(
                    source = "frog",
                    assert = true
                ),
                Option(
                    source = "zebra",
                    assert = false
                ),
                Option(
                    source = "pig",
                    assert = false
                ),
                Option(
                    source = "rat",
                    assert =false
                ),
        )
    ),
        Sector(
            source = "/video/twoCut.mp4",
            options = listOf(

                Option(
                    source = "dino",
                    assert = false
                ),
                Option(
                    source = "duck",
                    assert =false
                ),
                Option(
                    source = "fish",
                    assert = false
                ),
                Option(
                    source = "rabbit",
                    assert =true
                ),
                Option(
                    source = "tiger",
                    assert = false
                ),
        )
    ),
        Sector(
            source = "/video/threeCut.mp4",
            options = listOf(

                Option(
                    source = "bee",
                    assert =false
                ),
                Option(
                    source = "snail",
                    assert =false
                ),
                Option(
                    source = "pig",
                    assert = true
                ),
                Option(
                    source = "frog",
                    assert = false
                ),
                Option(
                    source = "dog2",
                    assert = false
                ),
        )
    ),
        Sector(
            source = "/video/fourCut.mp4",
            options = listOf(

                Option(
                    source = "fish",
                    assert =true
                ),
                Option(
                    source = "snake",
                    assert =false
                ),
                Option(
                    source = "lion",
                    assert = false
                ),
                Option(
                    source = "turtle",
                    assert = false
                ),
                Option(
                    source = "elephant",
                    assert = false
                ),
        )
    ),
        Sector(
            source = "/video/fiveCut.mp4",
            options = listOf(

                Option(
                    source = "frog",
                    assert =false
                ),
                Option(
                    source = "butterfly",
                    assert = false
                ),
                Option(
                    source = "rat",
                    assert =false
                ),
                Option(
                    source = "zebra",
                    assert = false
                ),
                Option(
                    source = "turtle",
                    assert = true
                ),
        )
    ),
        Sector(
            source = "/video/sixCut.mp4",
            options = listOf(

                Option(
                    source = "snail",
                    assert =false
                ),
                Option(
                    source = "rabbit",
                    assert =false
                ),
                Option(
                    source = "fish",
                    assert = false
                ),
                Option(
                    source = "butterfly",
                    assert = true
                ),
                Option(
                    source = "tiger",
                    assert = false
                ),
        )
    ),
        Sector(
            source = "/video/sevenCut.mp4",
            options = listOf(

                Option(
                    source = "duck",
                    assert =false
                ),
                Option(
                    source = "elephant",
                    assert =true
                ),
                Option(
                    source = "lion",
                    assert = false
                ),
                Option(
                    source = "bee",
                    assert = false
                ),
                Option(
                    source = "snake",
                    assert = true
                ),
        )
    ),
    )
    return Movie(
        source = "/video/fulltv.mp4",
        sector = sector
    )
}
