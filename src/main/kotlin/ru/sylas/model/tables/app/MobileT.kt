package ru.sylas.model.tables.app

import org.jetbrains.exposed.dao.id.IntIdTable

object MobileT: IntIdTable("mobile"){
    val deviceId = text("deviceId")
    val appId = reference("appId",AppT)
    val device = text("device")
}