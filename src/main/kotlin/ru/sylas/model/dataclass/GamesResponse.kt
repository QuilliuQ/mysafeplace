package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response

@Response
data class GamesResponse(
    val name:String,
    val image:String
)
