package ru.sylas.model.tables.stats

import org.jetbrains.exposed.dao.id.UUIDTable
import ru.sylas.model.tables.auth.UserKeyDeviceT

object StatsT : UUIDTable("stats"){
    val userStatsId = reference("userStat",UserStatT)
    val userKeyDeviceId = reference("userKeyDeviceId", UserKeyDeviceT)
}