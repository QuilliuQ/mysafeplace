package ru.sylas.service.statisticservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.Stats
import ru.sylas.repository.stats.StatisticRepository

class StatisticServiceImpl(private val repository: StatisticRepository): StatisticService {
    override fun sendStat(key: KeyDevice, stats: Stats) {
         repository.sendStat(key, stats)
    }

    override fun getStat(keyDevice: KeyDevice): List<Stats> {
        return repository.getStat(keyDevice)
    }
}