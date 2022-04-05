package ru.sylas.model.tables.tv

import org.jetbrains.exposed.dao.id.IntIdTable

object TVStat:IntIdTable("tvstat") {
    val name =text("name")
    val errors = integer("errors")
    val timeInGame = integer("time")
}