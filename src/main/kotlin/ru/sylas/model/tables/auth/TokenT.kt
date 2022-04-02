package ru.sylas.model.tables.auth

import org.jetbrains.exposed.dao.id.UUIDTable

object TokenT : UUIDTable("token"){
    val userId = reference("userId",UserTableT)
    val userKeyDeviceId = reference("userKeyDeviceId",UserKeyDeviceT)
    val accessToken = text("token")
}