package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Request

@Request
data class NewTV(
    val deviceId:String,
    val playerName:String,
    val device:String
)
