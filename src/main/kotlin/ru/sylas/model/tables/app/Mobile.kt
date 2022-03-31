package ru.sylas.model.tables.app

import org.jetbrains.exposed.dao.id.IntIdTable

object Mobile: IntIdTable("mobile"){
    val deviceId = text("deviceId")
    val appId = reference("appId",App)
    val device = text("device")
}