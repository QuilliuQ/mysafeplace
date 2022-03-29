package ru.sylas.service.appservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile
import ru.sylas.repository.apprepository.AppRepository

class AppServiceImpl(private val repository: AppRepository):AppService {
    override fun regApp(newApp: NewApp) {
        repository.regApp(newApp)
    }

    override fun getApps(competitor: Competitor): List<NewApp> {
        return repository.getApps(competitor)
    }

    override fun regMobile(newMobile: NewMobile): KeyDevice {
        return repository.regMobile(newMobile)
    }

    override fun getMobile(appId: AppId): List<NewMobile> {
        return repository.getMobile(appId)
    }
}