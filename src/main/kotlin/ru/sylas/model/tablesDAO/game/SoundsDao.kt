package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.SourceT

class SoundsDao(id:EntityID<Int>): IntEntity(id) {
    companion object:IntEntityClass<SoundsDao>(SourceT)

    var path by SourceT.path
    var sourceId by SourceT.sourceId
}