package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object SourceT : IntIdTable("sourceT") {
    val path = text("path")
    val sourceId = integer("sourceId")

}