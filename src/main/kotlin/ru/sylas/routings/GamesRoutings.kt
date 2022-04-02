package ru.sylas.routings




import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Utils.auth
import ru.sylas.common.myApiRouting
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.common.Tag
import ru.sylas.model.dataclass.Correspond
import ru.sylas.model.dataclass.GameSize
import ru.sylas.model.dataclass.Games
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.GameID
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.service.gameservice.GameService


fun Application.gameRouting(){

    val service: GameService by inject()

    myApiRouting {
        tag(Tag.Games){
               auth{
        route("/games") {
                   this.get<Unit, List<GamesResponse>, AuthUser>(
                       info(
                           summary = "Получение списка игр"
                       ),
                       example = listOf(GamesResponse(GameType.Numbers,"image.png",GameSize.Small))
                   ) {
                       respond(service.getGames())
                   }
               }
                   route("/game") {
                   this.get<GameID, Games, AuthUser>(
                       info(
                           summary = "Получение конкретной игры"
                       ),
                       example = Games.NumberGame(
                           GameType.Numbers,
                           listOf(Correspond("one.ogg", "1")),
                           listOf(Correspond("one.png", "1"))
                       )
                   ) {id->

                       respond(service.getGame(GameType.Numbers))
                   }
               }

           }

        }
        }
    }
