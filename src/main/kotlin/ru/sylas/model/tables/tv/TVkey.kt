package ru.sylas.model.tables.tv

import org.jetbrains.exposed.dao.id.IntIdTable

object TVkey:IntIdTable("TVkey") {
    val tvId = reference("tvId",TV)
    val keyDevice = text("keyDevice")
}