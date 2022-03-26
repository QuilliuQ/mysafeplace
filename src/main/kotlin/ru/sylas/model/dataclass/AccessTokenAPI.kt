package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.parameters.HeaderParam

data class AccessTokenAPI(
    @HeaderParam("Auth token")
    val Authorization : String?
)

