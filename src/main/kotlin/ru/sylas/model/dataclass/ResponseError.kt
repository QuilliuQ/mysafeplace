package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response
import io.ktor.http.*




data class ResponseError(
    val statusCode: HttpStatusCode,
    val message:String?
)