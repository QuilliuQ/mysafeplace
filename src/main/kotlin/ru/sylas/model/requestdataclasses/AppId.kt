package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.parameters.QueryParam

data class AppId(
    @QueryParam("appID") val appId: String
)
