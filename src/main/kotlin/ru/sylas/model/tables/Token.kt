package ru.sylas.model.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object Token : UUIDTable("token"){
    val accessToken = text("token")
    val refreshToken = text("refreshToken")
    val expireAt = long("expireAt")
}