package ru.sylas.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPages() {
        install(StatusPages) {
            status(HttpStatusCode.NotFound){
                call.respond(HttpStatusCode.NotFound, "Несуществующий запрос")
            }
            status(HttpStatusCode.InternalServerError){
                call.respond(HttpStatusCode.InternalServerError, "Внутренная ошибка сервера")
            }
        }
}