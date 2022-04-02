package ru.sylas.repository.stats

import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.DontHaveStatsException
import ru.sylas.exceptions.UnknownGameTypeException
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tables.stats.StatsT
import ru.sylas.model.tablesDAO.game.GameTypeDao
import ru.sylas.model.tablesDAO.stats.StatDao
import ru.sylas.model.tablesDAO.stats.UserStatDao
import ru.sylas.model.tablesDAO.stats.toStatList
import ru.sylas.repository.auth.getUserKeyDevice

class StatisticRepositoryImpl : StatisticRepository {
    override fun sendStat(key: KeyDevice, stats: Stats) {
        loggedTransaction {

            val userStat = UserStatDao.new {
                this.type = getTypeDao(stats.type)
                this.errors = stats.errors
                this.timeInGame = stats.timeInGame
            }
            StatDao.new {
                this.userStatId = userStat
                this.userKeyDeviceId = getUserKeyDevice(key)
            }
        }
    }

    override fun getStat(keyDevice: KeyDevice): List<Stats> {
        return loggedTransaction  {
            val keyDeviceDB = getUserKeyDevice(keyDevice)
            StatDao.find { StatsT.userKeyDeviceId eq keyDeviceDB.id }.toList()
                .guard {
                    throw DontHaveStatsException()
                }.toStatList()
        }
    }
}

fun getTypeDao(type:GameType) = GameTypeDao.find { GameTypeT.type eq type.name }.firstOrNull().guard {
    throw UnknownGameTypeException()
}