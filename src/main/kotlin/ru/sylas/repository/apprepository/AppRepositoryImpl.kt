package ru.sylas.repository.apprepository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.HellException
import ru.sylas.exceptions.UnknownAppID
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile
import ru.sylas.model.tables.app.App
import ru.sylas.model.tables.app.Mobile
import ru.sylas.model.tablesDAO.app.*

class AppRepositoryImpl:AppRepository {
    override fun regApp(newApp: NewApp) {
        try{
            loggedTransaction {
                AppDao.new {
                    appId = newApp.appId
                    competitor = newApp.competitor
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
            throw HellException(e.localizedMessage)
        }
    }

    override fun getApps(competitor: Competitor): List<NewApp> {
        try {
            return loggedTransaction {
                AppDao.find(App.competitor eq competitor.competitor).toList().map {
                    it.toNewApp()
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
            throw HellException(e.localizedMessage)
        }
    }

    override fun regMobile(newMobile: NewMobile): KeyDevice {
        try{
            return loggedTransaction {
                val appId = AppDao.find(App.appId eq newMobile.appId).firstOrNull().guard {
                    throw UnknownAppID("НЕТ айди")

                }
                val id = MobileDao.new {
                    deviceId = newMobile.deviceId
                    this.appId = appId
                    device = newMobile.device
                }
                KeyDeviceDao.new{
                        mobileId = id
                        keyDevice = (mobileId.device+mobileId.appId).sha256()
                    }.toKey()
            }
        }
        catch (e:Exception){
            e.printStackTrace()
            throw HellException(e.localizedMessage)

        }
    }

    override fun getMobile(appId: AppId): List<NewMobile> {
        try {
           return loggedTransaction {
                val app = AppDao.find(App.appId eq appId.appId).first()
                MobileDao.find(Mobile.appId eq app.id).toList().map {
                    it.toNewMobile()
                }

            }
        }
        catch (e:Exception){
            e.printStackTrace()
            throw HellException(e.localizedMessage)
        }
    }
}