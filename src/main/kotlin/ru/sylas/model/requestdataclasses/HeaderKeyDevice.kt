package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.parameters.HeaderParam
import ru.sylas.model.dataclass.KeyDevice

data class HeaderKeyDevice(
    @HeaderParam("keyDevice")
    val keyDevice : String
)

fun HeaderKeyDevice.toKeyDevice():KeyDevice =
    KeyDevice(this.keyDevice)