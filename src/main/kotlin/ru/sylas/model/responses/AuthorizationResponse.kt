package ru.sylas.model.responses

import io.ktor.http.*
import ru.sylas.model.dataclass.UserToken

data class AuthorizationResponse(
    var data: UserToken?= null,
    var error: String? = null,
    var statusCode: HttpStatusCode = HttpStatusCode.GatewayTimeout
)