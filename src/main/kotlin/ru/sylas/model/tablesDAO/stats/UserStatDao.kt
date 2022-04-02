package ru.sylas.model.tablesDAO.stats

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.stats.UserStatT
import ru.sylas.model.tablesDAO.game.GameTypeDao

class UserStatDao(id: EntityID<Int>):IntEntity(id) {
    companion object  : IntEntityClass<UserStatDao>(UserStatT)

    var type by GameTypeDao referencedOn UserStatT.type
    var errors by UserStatT.errors
    var timeInGame by UserStatT.timeInGame
}