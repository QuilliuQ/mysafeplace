package ru.sylas.service.appservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile

class AppServiceImpl:AppService {
    override fun regApp(newApp: NewApp) {

    }

    override fun getApps(competitor: Competitor): List<NewApp> {
        return emptyList()
    }

    override fun regMobile(newMobile: NewMobile): KeyDevice {
        return KeyDevice("")
    }

    override fun getMobile(appId: AppId): List<NewMobile> {
        return emptyList()
    }
}