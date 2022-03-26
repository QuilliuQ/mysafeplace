package ru.sylas.routings

import com.papsign.ktor.openapigen.route.apiRouting
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.path.normal.route
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import com.papsign.ktor.openapigen.route.tag
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.coroutines.flow.onEach
import org.koin.ktor.ext.inject
import ru.sylas.common.Resource
import ru.sylas.model.dataclass.Tag
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.service.AuthorizationService
import java.util.*

fun Application.configureRouting() {

    val service: AuthorizationService by inject()


    apiRouting {
        tag(Tag.Auth) {


            route("/auth") {
                route("/register") {
                    post<Unit, UserToken, NewUser> { _, body ->
                        respond(UserToken(userId = UUID.randomUUID(), "sha256"))
                    }

                }
                route("/login") {
                    post<Unit, UserToken, AuthUser>(
                        info(
                            summary = "Регистрация пользователя"
                        ),
                        exampleRequest = AuthUser("vasya@mail.com", password = "qwerty", hash = "sha256"),
                        exampleResponse = UserToken(userId = UUID.randomUUID(), "sha256")
                    )
                    { _, body ->
//                        service.authorization(body).onEach { response->
//                            when(response){
//                                is Resource.Error -> TODO()
//                                is Resource.Success -> TODO()
//                            }
//                        }
                        respond(UserToken(userId = UUID.randomUUID(), "sha256 ${body.login}"))
                    }
                }
            }
        }
    }
}