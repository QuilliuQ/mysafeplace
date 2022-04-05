package ru.sylas.service.tvservice

import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.NewTV

interface TVService {

    fun sendStat(key:KeyDevice, stat: TVStats)
    fun getStat(): List<TVStats>
    fun regApp(newTV: NewTV):KeyDevice
}