package ru.sylas.service.tvservice

import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.NewTV

interface TVService {
    fun getMovie(): Movie
    fun sendStat(key:KeyDevice, stat: TVStats)
    fun getStat():List<Statistic>
    fun regApp(newTV: NewTV):KeyDevice
}