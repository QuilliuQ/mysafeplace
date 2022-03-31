package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object GameTypeT: IntIdTable("gameType") {
    val type = text("type")
}
