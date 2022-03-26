package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Request

@Request
data class NewMobile(
    val deviceId:String,
    val appId:String,
    val device:String
)