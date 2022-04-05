package ru.sylas.model.tables.tv

import org.jetbrains.exposed.dao.id.IntIdTable

object TV:IntIdTable("tvdevices") {
    val deviceId = text("deviceId")
    val playerName = text("playerName")
    val device = text("device")
}