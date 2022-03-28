package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response

@Response
data class PhoneUser(
    val keyDevice: String,
    val name: String
)
