package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object AnimalT: IntIdTable("animal") {
    val name = text("name")
}