package ru.sylas.model.tables.watch

import org.jetbrains.exposed.dao.id.IntIdTable

object WatchKeysT: IntIdTable("watchkeys") {
    val name = text("name")
    val keyDevice = text("123")
}