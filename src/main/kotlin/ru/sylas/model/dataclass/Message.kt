package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response

@Response
data class Message (
    val message:String)


fun String.toResponseMessage() = Message(this)