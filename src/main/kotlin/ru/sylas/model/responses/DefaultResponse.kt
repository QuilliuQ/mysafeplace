package ru.sylas.model.responses

import io.ktor.http.*

data class DefaultResponse(
    var message: String = "Сервер недоступен",
    var statusCode: HttpStatusCode = HttpStatusCode.GatewayTimeout
)
