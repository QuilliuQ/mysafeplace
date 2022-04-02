package ru.sylas.model.tablesDAO.stats

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.tables.stats.StatsT
import ru.sylas.model.tablesDAO.auth.UserKeyDeviceDao
import ru.sylas.model.tablesDAO.game.getType
import java.util.*
import kotlin.collections.List

class StatDao(id : EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<StatDao>(StatsT)

    var userStatId by UserStatDao referencedOn StatsT.userStatsId
    var userKeyDeviceId by UserKeyDeviceDao referencedOn StatsT.userKeyDeviceId
}

fun List<StatDao>.toStatList() =
    this.map {
    it.toStats()
}

fun StatDao.toStats() = Stats(
    errors = this.userStatId.errors,
    timeInGame = this.userStatId.timeInGame,
    type = getType(this.userStatId.type.type)
)