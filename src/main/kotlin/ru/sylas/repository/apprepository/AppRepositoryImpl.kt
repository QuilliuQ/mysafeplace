package ru.sylas.repository.apprepository

import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile

class AppRepositoryImpl:AppRepository {
    override fun regApp(newApp: NewApp) {
        transaction {

        }
    }

    override fun getApps(competitor: Competitor): List<NewApp> {
        TODO("Not yet implemented")
    }

    override fun regMobile(newMobile: NewMobile): KeyDevice {
        TODO("Not yet implemented")
    }

    override fun getMobile(appId: AppId): List<NewMobile> {
        TODO("Not yet implemented")
    }
}