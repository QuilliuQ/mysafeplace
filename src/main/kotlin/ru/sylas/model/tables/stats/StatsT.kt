package ru.sylas.model.tables.stats

import org.jetbrains.exposed.dao.id.UUIDTable
import ru.sylas.model.tables.auth.UserKeyDevice

object StatsT : UUIDTable("stats"){
    val userStatsId = reference("userStat",UserStat)
    val userKeyDeviceId = reference("userKeyDeviceId", UserKeyDevice)
}