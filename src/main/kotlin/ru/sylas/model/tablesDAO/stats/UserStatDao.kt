package ru.sylas.model.tablesDAO.stats

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.stats.UserStat
import ru.sylas.model.tablesDAO.game.GameTypeDao

class UserStatDao(id: EntityID<Int>):IntEntity(id) {
    companion object  : IntEntityClass<UserStatDao>(UserStat)

    var type by GameTypeDao referencedOn UserStat.type
    var errors by UserStat.errors
    var timeInGame by UserStat.timeInGame
}