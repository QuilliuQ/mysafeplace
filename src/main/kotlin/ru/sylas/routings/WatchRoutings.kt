package ru.sylas.routings

import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import com.papsign.ktor.openapigen.route.tag
import io.ktor.application.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Tag
import ru.sylas.common.Utils.auth
import ru.sylas.common.myApiRouting
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.HeaderKeyDevice
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.model.requestdataclasses.toKeyDevice
import ru.sylas.service.watchservice.WatchService

fun Application.configureWatch(){

    val service:WatchService by inject()


    myApiRouting {
        tag(Tag.Watch){
            auth{
                route("/devices") {
                    get<Unit, List<PhoneUser>, AuthUser>(
                        info(
                            summary = "Получение подключенных к аккаунту устройств"
                        ),
                        example = listOf(PhoneUser("qwerdsa134ed", "Xiaomi Mi A3 Vitaliy"))
                    ) {
                        respond(service.getDevices())
                    }
                    route("/pin")
                    {
                        get<HeaderKeyDevice, PinCode,AuthUser>(
                            info(
                                "Получение пинкода авторизации для устройства"
                            ),
                            example = PinCode(1423)
                        ) { key ->
                            respond(service.getPinCode(key.toKeyDevice()))
                        }
                    }
                    route("/statistic") {
                        get<HeaderKeyDevice, List<Stats>,AuthUser>(
                            info(
                                summary = "Получение статистики устройства"
                            )
                        ) { key ->
                            respond(service.getStats(key.toKeyDevice()))
                        }
                    }

                }
            }
        }
    }


}