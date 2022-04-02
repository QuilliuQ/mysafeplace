package ru.sylas.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import ru.sylas.model.dataclass.ErrorEx

fun Application.configureStatusPages() {
        install(StatusPages) {
            status(HttpStatusCode.NotFound){
                call.respond(HttpStatusCode.NotFound, ErrorEx("Несуществующий запрос"))
            }
            status(HttpStatusCode.InternalServerError){
                call.respond(HttpStatusCode.InternalServerError, ErrorEx("Внутренная ошибка сервера"))
            }
        }
}