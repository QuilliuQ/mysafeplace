package ru.sylas.model.tablesDAO.tv

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.tv.TVStat

class TVStatDao(id:EntityID<Int>):IntEntity(id) {
    companion object:IntEntityClass<TVStatDao>(TVStat)

    var name by TVStat.name
    var errors by TVStat.errors
    var timeInGame by TVStat.timeInGame
}