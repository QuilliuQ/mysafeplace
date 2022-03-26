package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Request
import java.util.*

@Request("hash String =  SHA256(keyDevice+login+uuid)")
data class AuthUser(
    val login:String,
    val password:String,
    val hash: String
)
