package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Request
import io.ktor.auth.*

@Request
data class AuthUser(
    val email:String,
    val password:String
):Principal
