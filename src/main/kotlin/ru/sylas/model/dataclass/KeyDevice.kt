package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response

@Response
data class KeyDevice(
    val keyDevice: String
)
