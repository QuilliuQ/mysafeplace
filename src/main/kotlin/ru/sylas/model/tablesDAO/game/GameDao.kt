package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.GameT
import ru.sylas.model.tables.game.GameTypeT

class GameDao(id : EntityID<Int>):IntEntity(id) {
    companion object : IntEntityClass<GameDao>(GameT)

    var type by GameTypeDao referencedOn GameT.type
    var image by GameT.image
    var size by SizeDao referencedOn GameT.size
}