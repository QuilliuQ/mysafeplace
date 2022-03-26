package ru.sylas.common

import io.ktor.http.*
import ru.sylas.model.dataclass.ResponseError

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: HttpStatusCode = HttpStatusCode.OK
){
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(message: String,statusCode: HttpStatusCode):Resource<T>(message = message, statusCode = statusCode)
}
