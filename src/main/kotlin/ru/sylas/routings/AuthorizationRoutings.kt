package ru.sylas.routings

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.http.*
import org.koin.ktor.ext.inject
import ru.sylas.common.myApiRouting
import ru.sylas.exceptions.UnauthorizedException
import ru.sylas.exceptions.UserAlreadyCreatedException
import ru.sylas.common.Tag
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.*
import ru.sylas.service.authorizationservice.AuthorizationService
import java.util.*

fun Application.authentificationRouting() {

    val service: AuthorizationService by inject()


    myApiRouting {
        tag(Tag.Auth) {
            route("/auth") {
                route("/register") {
                    throws(
                        status = HttpStatusCode.Conflict.description("Имя пользователя занято"),
                        gen = { e: UserAlreadyCreatedException -> return@throws e.localizedMessage}
                    )
                        {
                        post<HeaderKeyDevice, UserToken, NewUser>(
                            info(
                                summary = "Регистрация пользователя"
                            ),
                            exampleRequest = NewUser(
                                "vasya@mail.com",
                                password = "qwerty",
                                childrenName = "Vasiliy",
                                parentName = "Ivan"
                            ),
                            exampleResponse = UserToken(
                                userId = UUID.randomUUID(),
                                "sha256"
                            )
                        ) { header, body ->
                            respond(service.registration(body,header.toKeyDevice()))
                        }
                    }
                }
                throws(
                    status = HttpStatusCode.Unauthorized.description("Неправильный логин или пароль"),
                    gen = { _: UnauthorizedException -> return@throws  "Неправильный логин или пароль"}
                )
                    .throws(
                        status = HttpStatusCode.BadRequest.description("Пользователя не существует"),
                        gen = { _: MissingKotlinParameterException -> return@throws "Проверьте корректность запроса" }
                    ){
                    route("/login") {
                        post<HeaderKeyDevice, UserToken, AuthUser>(
                            info(
                                summary = "Авторизация пользователя"
                            ),
                            exampleRequest = AuthUser("vasya@mail.com", password = "qwerty"),
                            exampleResponse = UserToken(userId = UUID.randomUUID(), "sha256")
                        )
                        { header, body ->
                            respond(service.authorization(body,header.toKeyDevice()))
                        }
                    }
                        route("/pin") {
                            post<HeaderKeyDevice, UserToken, PinCode>(
                                info(
                                    summary = "Авторизация пользователя"
                                ),
                                exampleRequest = PinCode(1232),
                                exampleResponse = UserToken(userId = UUID.randomUUID(), "sha256")
                            )
                            { header, pin ->
                                respond(service.pinAuthorization(pin,header.toKeyDevice()))
                            }
                        }
                }
                    }
                }
            }
        }


