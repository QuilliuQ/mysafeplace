package ru.sylas.model.tables.auth

import org.jetbrains.exposed.dao.id.UUIDTable

object Token : UUIDTable("token"){
    val userId = reference("userId",UserTable)
    val userKeyDeviceId = reference("userKeyDeviceId",UserKeyDevice)
    val accessToken = text("token")
}