package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object SourceTypeT: IntIdTable("sourceType") {
    val type = text("type")

}
