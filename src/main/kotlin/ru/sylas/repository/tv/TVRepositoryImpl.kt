package ru.sylas.repository.tv

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.Utils
import ru.sylas.common.Utils.guard
import ru.sylas.exceptions.BadKeyDeviceException
import ru.sylas.exceptions.DontHaveStatsException
import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.NewTV
import ru.sylas.model.tables.stats.StatsT
import ru.sylas.model.tables.tv.TVkey
import ru.sylas.model.tablesDAO.app.KeyDeviceDao
import ru.sylas.model.tablesDAO.app.toKey
import ru.sylas.model.tablesDAO.stats.StatDao
import ru.sylas.model.tablesDAO.stats.UserStatDao
import ru.sylas.model.tablesDAO.stats.toStatList
import ru.sylas.model.tablesDAO.tv.TVDao
import ru.sylas.model.tablesDAO.tv.TVStatDao
import ru.sylas.model.tablesDAO.tv.TVkeyDao
import ru.sylas.model.tablesDAO.tv.toKey
import ru.sylas.repository.auth.getUserKeyDevice
import ru.sylas.repository.stats.getTypeDao

class TVRepositoryImpl:TVRepository {
    override fun regMobile(newTV: NewTV): KeyDevice {
        return Utils.loggedTransaction {
            val id = TVDao.new {
                deviceId = newTV.deviceId
               playerName = newTV.playerName
                device = newTV.device
            }
            TVkeyDao.new {
                tvId = id
                keyDevice = (id.device + id.deviceId).sha256()
            }.toKey()
        }

    }

    override fun sendStat(key: KeyDevice, stats: TVStats) {
        Utils.loggedTransaction {
            val keyDB = TVkeyDao.find { TVkey.keyDevice eq key.keyDevice }
                .firstOrNull().guard {
                    throw BadKeyDeviceException()
                }
            TVStatDao.new {
                this.timeInGame = stats.timeInGame
                this.name = stats.name
                this.errors = stats.errors
            }
        }
    }

    override fun getStat(): List<TVStats> {
        return Utils.loggedTransaction {
             TVStatDao.all().toList().map {
                 it.toTVStats()
             }
        }
    }

}

private fun TVStatDao.toTVStats() = TVStats(
    name, errors, timeInGame
)
