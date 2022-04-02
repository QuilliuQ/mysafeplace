package ru.sylas.routings

import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.http.*
import org.koin.ktor.ext.inject
import ru.sylas.common.myApiRouting
import ru.sylas.common.Tag
import ru.sylas.exceptions.*
import ru.sylas.model.dataclass.ErrorEx
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.*
import ru.sylas.service.authorizationservice.AuthorizationService
import java.util.*

fun Application.authorizationRouting() {

    val service: AuthorizationService by inject()


    myApiRouting {
        tag(Tag.Auth) {
            throws(
                status = HttpStatusCode.Unauthorized.description("KeyDevice не зарегистрирован"),
                example = ErrorEx("KeyDevice не зарегистрирован"),
                BadKeyDeviceException::class
            ).route("/auth") {
                route("/register") {
                    throws(
                        status = HttpStatusCode.Conflict.description("Имя пользователя занято"),
                        example = ErrorEx("Имя пользователя занято"),
                        UserAlreadyCreatedException::class
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
                    status = HttpStatusCode.Forbidden.description("Неправильный логин или пароль"),
                    example = ErrorEx("Неправильный логин или пароль"),
                    BadCredentialsException::class
                ){
                    route("/login") {
                        post<HeaderKeyDevice, UserToken, AuthUser>(
                            info(
                                summary = "Авторизация пользователя c помощью email"
                            ),
                            exampleRequest = AuthUser("vasya@mail.com", password = "qwerty"),
                            exampleResponse = UserToken(userId = UUID.randomUUID(), "sha256")
                        )
                        { header, body ->
                            respond(service.authorization(body,header.toKeyDevice()))
                        }
                    }}
                    throws(
                        status = HttpStatusCode.Unauthorized.description("Данное устройство не зарегистрировано пользователем"),
                        example = ErrorEx("Данное устройство не зарегистрировано пользователем"),
                        BadUserKeyDeviceException::class
                    ).route("/pin") {
                            throws(
                                status = HttpStatusCode.Forbidden.description("Введден неверный пин код"),
                                example = ErrorEx("Введден неверный пин код"),
                                BadPinCodeException::class
                            ).post<HeaderKeyDevice, UserToken, PinCode>(
                                info(
                                    summary = "Авторизация пользователя c помощью Пин кода"
                                ),
                                exampleRequest = PinCode(1232),
                                exampleResponse = UserToken(userId = UUID.randomUUID(), "sha256")
                            )
                            { header, pin ->
                                respond(service.pinAuthorization(pin,header.toKeyDevice()))
                            }
                            get<HeaderKeyDevice,PinCode>(
                                info(
                                    summary = "Генерация пин кода"
                                ),
                                example = PinCode(1232),
                            ){header->
                                respond(service.genPinCode(header.toKeyDevice()))
                            }
                        }
                }
                    }
                }
            }


