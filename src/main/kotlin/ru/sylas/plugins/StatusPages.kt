package ru.sylas.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPages() {
        install(StatusPages) {
            status(HttpStatusCode.NotFound){
                call.respond(HttpStatusCode.NotFound, mapOf("Error" to "Несуществующий запрос"))
            }
            status(HttpStatusCode.BadRequest){
                call.respond(HttpStatusCode.BadRequest, mapOf("Error" to "Проверьте корректность запроса"))
            }
        }
}