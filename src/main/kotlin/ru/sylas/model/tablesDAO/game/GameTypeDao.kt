package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.GameT
import ru.sylas.model.tables.game.GameTypeT

class GameTypeDao(id : EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<GameTypeDao>(GameTypeT)

    var type by GameTypeT.type
}