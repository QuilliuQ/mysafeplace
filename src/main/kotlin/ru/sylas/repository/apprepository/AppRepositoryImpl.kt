package ru.sylas.repository.apprepository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.BadAppIdException
import ru.sylas.exceptions.UnknownAppIDException
import ru.sylas.exceptions.UnknownCompetitorException
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile
import ru.sylas.model.tables.app.App
import ru.sylas.model.tables.app.Mobile
import ru.sylas.model.tablesDAO.app.*

class AppRepositoryImpl : AppRepository {
    override fun regApp(newApp: NewApp) {
        loggedTransaction {
            AppDao.new {
                appId = newApp.appId
                competitor = newApp.competitor
            }
        }


    }

    override fun getApps(competitor: Competitor): List<NewApp> {
        return loggedTransaction {
            val app = AppDao.find(App.competitor eq competitor.competitor)
            if (!app.empty()) {
                app.toList().map {
                    it.toNewApp()
                }
            } else {
                throw UnknownCompetitorException()
            }

        }
    }

    override fun regMobile(newMobile: NewMobile): KeyDevice {
        return loggedTransaction {
            val appId = AppDao.find(App.appId eq newMobile.appId).firstOrNull().guard {
                throw UnknownAppIDException()
            }
            val id = MobileDao.new {
                deviceId = newMobile.deviceId
                this.appId = appId
                device = newMobile.device
            }
            KeyDeviceDao.new {
                mobileId = id
                keyDevice = (id.device + id.appId).sha256()
            }.toKey()
        }

    }

    override fun getMobile(appId: AppId): List<NewMobile> {
        return loggedTransaction {
            val app = AppDao.find(App.appId eq appId.appId).firstOrNull().guard {
                throw UnknownAppIDException()
            }
            val mobile = MobileDao.find(Mobile.appId eq app.id)
            if (!mobile.empty()) {
                mobile.toList().map {
                    it.toNewMobile()
                }
            } else {
                throw BadAppIdException()
            }

        }
    }
}