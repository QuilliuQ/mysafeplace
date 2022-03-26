package ru.sylas.model.dataclass

import io.ktor.http.*

data class ResponseError(
    val statusCode: HttpStatusCode,
    val message:String?
)