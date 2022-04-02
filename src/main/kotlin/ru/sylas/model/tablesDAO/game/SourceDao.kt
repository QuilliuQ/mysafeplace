package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.SourceT

class SourceDao(id:EntityID<Int>): IntEntity(id) {
    companion object:IntEntityClass<SourceDao>(SourceT)

    var path by SourceT.path
    val sourceId by SourceT.sourceId
}