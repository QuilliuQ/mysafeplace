package ru.sylas.routings




import com.papsign.ktor.openapigen.annotations.Response
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import ru.sylas.common.Utils.auth
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.dataclass.Tag

@Response
data class Error(val error: String)
data class GameID(@QueryParam("Имя конкретной игры") val name: String)
fun Application.gameRoutings(){
    apiRouting {


        tag(Tag.Games){
        route("/games") {
           throws(HttpStatusCode.BadRequest, null, { ex: Exception -> Error(ex.localizedMessage)}) {
               auth{
                   this.get<Unit, List<GamesResponse>, UserIdPrincipal>(
                       info(
                           summary = ""


                       ),
                       example = emptyList()
                   ) {

                       respond(listOf(GamesResponse("", "")))
                   }
               }

           }

        }
        }
    }
}