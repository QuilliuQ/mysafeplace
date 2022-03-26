package ru.sylas.service.statisticservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.Stats

class StatisticServiceImpl: StatisticService {
    override fun sendStat(key: KeyDevice, stats: Stats) {

    }

    override fun getStat(keyDevice: KeyDevice): List<Stats> {
        return emptyList()
    }
}