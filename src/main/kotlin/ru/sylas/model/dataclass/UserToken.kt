package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response


@Response("Token")
data class UserToken(
    val userId: String,
    val accessToken: String
)

