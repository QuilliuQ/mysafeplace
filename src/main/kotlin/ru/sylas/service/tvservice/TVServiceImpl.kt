package ru.sylas.service.tvservice

import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.NewTV
import ru.sylas.model.tables.tv.TV
import ru.sylas.repository.tv.TVRepository
import ru.sylas.repository.tv.TVRepositoryImpl

class TVServiceImpl(private val repository:TVRepository):TVService {


    override fun sendStat(key: KeyDevice, stat: TVStats) {
        repository.sendStat(key,stat)
    }

    override fun getStat(): List<TVStats> {
        return repository.getStat()
    }

    override fun regApp(newTV: NewTV): KeyDevice {
       return repository.regMobile(newTV)
    }
}