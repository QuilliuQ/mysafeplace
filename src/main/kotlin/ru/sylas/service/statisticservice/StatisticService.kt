package ru.sylas.service.statisticservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.Stats

interface StatisticService {
    fun sendStat(key: KeyDevice, stats: Stats)
    fun getStat(keyDevice: KeyDevice):List<Stats>
}