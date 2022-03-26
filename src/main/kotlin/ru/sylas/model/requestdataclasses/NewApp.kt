package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Request

@Request
data class NewApp(
    val appId:String,
    val competitor:String
)
