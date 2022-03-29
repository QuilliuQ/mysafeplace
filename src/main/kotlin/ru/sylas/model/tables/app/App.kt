package ru.sylas.model.tables.app

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable

object App : IntIdTable("app") {
    val appId = text("appId")
    val competitor = text("competitor")
}


object Mobile:IntIdTable("mobile"){
    val deviceId = text("deviceId")
    val appId = reference("appId",App)
    val device = text("device")
}