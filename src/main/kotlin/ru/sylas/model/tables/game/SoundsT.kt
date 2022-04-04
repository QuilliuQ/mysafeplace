package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object SoundsT : IntIdTable("sourceT") {
    val path = text("path")
    val sourceId = text("sourceId")

}