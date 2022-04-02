package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.CorrespondingT

class CorrespondingDao(id:EntityID<Int>):IntEntity(id) {
    companion object : IntEntityClass<CorrespondingDao>(CorrespondingT)
    var type  by GameTypeDao referencedOn CorrespondingT.type
    var a by SourceDao referencedOn CorrespondingT.a
    var b by SourceDao referencedOn CorrespondingT.b
}