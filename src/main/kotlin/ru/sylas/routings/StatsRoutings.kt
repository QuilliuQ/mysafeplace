package ru.sylas.routings




import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.path.auth.post
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.auth.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Utils.auth
import ru.sylas.common.myApiRouting
import ru.sylas.common.Tag
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.requestdataclasses.HeaderKeyDevice
import ru.sylas.model.requestdataclasses.toKeyDevice
import ru.sylas.service.statisticservice.StatisticService


fun Application.statsRouting(){

    val service: StatisticService by inject()

    myApiRouting {
        tag(Tag.Statistic){
               auth {
                   route("/statistic") {
                       this.get<HeaderKeyDevice, List<Stats>, UserIdPrincipal>(
                           info(
                               summary = "Получение статистики игр"
                           ),
                           example = listOf(Stats(GameType.Numbers, 0, 120))
                       ) { key ->

                           respond(service.getStat(key.toKeyDevice()))
                       }


                       this.post<HeaderKeyDevice, Unit, Stats, UserIdPrincipal>(
                           info(
                               summary = "Отправление статистики"
                           ),
                           exampleRequest = Stats(
                               GameType.Numbers,
                               0, 20
                           )
                       ) { key, stat ->
                           service.sendStat(key.toKeyDevice(), stat)
                       }
                   }
               }
           }

        }
        }

