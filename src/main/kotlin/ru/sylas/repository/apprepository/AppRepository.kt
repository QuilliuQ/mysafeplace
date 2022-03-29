package ru.sylas.repository.apprepository

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.AppId
import ru.sylas.model.requestdataclasses.Competitor
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile

interface AppRepository {
    fun regApp(newApp: NewApp)
    fun getApps(competitor: Competitor):List<NewApp>
    fun regMobile(newMobile: NewMobile): KeyDevice
    fun getMobile(appId: AppId):List<NewMobile>
}