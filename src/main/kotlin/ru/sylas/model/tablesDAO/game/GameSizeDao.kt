package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.GameSizeT

class GameSizeDao(id : EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<GameSizeDao>(GameSizeT)

    var size by GameSizeT.size
}