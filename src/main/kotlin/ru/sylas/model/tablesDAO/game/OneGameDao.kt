package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.GameSourcesT
import ru.sylas.model.tables.game.OneGameT

class OneGameDao(id:EntityID<Int>):IntEntity(id) {
    companion object:IntEntityClass<OneGameDao>(OneGameT)
    var correspondings by CorrespondingDao referencedOn  OneGameT.corresponding
    var sounds by SourceDao via GameSourcesT
    var sources by SourceDao via GameSourcesT
}