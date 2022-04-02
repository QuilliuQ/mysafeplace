package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object CorrespondingT : IntIdTable("corresponding") {
    val type = reference("type",GameTypeT)
    val a = reference("a",SourceT)
    val b = reference("b",SourceT)
}