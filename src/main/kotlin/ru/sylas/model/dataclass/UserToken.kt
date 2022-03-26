package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response
import java.util.*


@Response("Token")
data class UserToken(
    val userId: UUID,
    val accessToken: String
)

