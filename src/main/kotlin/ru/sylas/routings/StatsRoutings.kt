package ru.sylas.routings




import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.path.auth.post
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.http.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Utils.auth
import ru.sylas.common.myApiRouting
import ru.sylas.common.Tag
import ru.sylas.exceptions.BadKeyDeviceException
import ru.sylas.exceptions.BadUserKeyDeviceException
import ru.sylas.exceptions.UnknownGameTypeException
import ru.sylas.model.dataclass.ErrorEx
import ru.sylas.model.dataclass.Message
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.dataclass.toResponseMessage
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.requestdataclasses.HeaderKeyDevice
import ru.sylas.model.requestdataclasses.toKeyDevice
import ru.sylas.service.statisticservice.StatisticService


fun Application.statsRouting(){

    val service: StatisticService by inject()

    myApiRouting {
        tag(Tag.Statistic){
               auth {
                   throws(
                       status = HttpStatusCode.BadRequest.description("KeyDevice не зарегистрирован"),
                       example = ErrorEx("KeyDevice не зарегистрирован"),
                       BadKeyDeviceException::class
                   ).throws(
                       status = HttpStatusCode.BadRequest.description("Данное устройство не зарегистрировано пользователем"),
                       example = ErrorEx("Данное устройство не зарегистрировано пользователем"),
                       BadUserKeyDeviceException::class
                   ).route("/statistic") {
                       get<HeaderKeyDevice, List<Stats>, AuthUser>(
                           info(
                               summary = "Получение статистики игр"
                           ),
                           example = listOf(Stats(GameType.Numbers, 0, 120))
                       ) { key ->
                           respond(service.getStat(key.toKeyDevice()))
                       }
                       throws(
                           status = HttpStatusCode.BadRequest.description("Неправильный тип игры"),
                           example = ErrorEx("Неправильный тип игры"),
                           UnknownGameTypeException::class
                       ).post<HeaderKeyDevice, Message, Stats, AuthUser>(
                           info(
                               summary = "Отправление статистики"
                           ),
                           exampleRequest = Stats(
                               GameType.Numbers,
                               0, 20
                           )
                       ) { key, stat ->
                           service.sendStat(key.toKeyDevice(), stat)
                           respond("OK".toResponseMessage())
                       }
                   }
               }
           }

        }
        }

