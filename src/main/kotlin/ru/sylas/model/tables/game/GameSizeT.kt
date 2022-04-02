package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object GameSizeT: IntIdTable("sizeGame") {
    val size = text("size")
}
