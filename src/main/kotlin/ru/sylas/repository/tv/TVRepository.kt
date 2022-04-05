package ru.sylas.repository.tv

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.Movie
import ru.sylas.model.dataclass.Statistic
import ru.sylas.model.dataclass.TVStats
import ru.sylas.model.requestdataclasses.NewMobile
import ru.sylas.model.requestdataclasses.NewTV

interface TVRepository {
    fun regMobile(newMobile: NewTV): KeyDevice
    fun sendStat(key: KeyDevice, stats: TVStats)
    fun getStat(): List<TVStats>

}