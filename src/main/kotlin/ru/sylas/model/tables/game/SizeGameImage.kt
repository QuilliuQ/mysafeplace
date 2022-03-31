package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object SizeGameImage: IntIdTable("sizeGame") {
    val size = text("size")
}
