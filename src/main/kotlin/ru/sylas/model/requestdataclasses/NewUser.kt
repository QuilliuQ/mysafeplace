package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Request

@Request
data class NewUser(
    val email:String,
    val password:String,
    val childrenName:String,
    val parentName:String
)
