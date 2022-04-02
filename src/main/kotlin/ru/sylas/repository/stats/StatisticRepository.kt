package ru.sylas.repository.stats

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.Stats

interface StatisticRepository {
    fun sendStat(key: KeyDevice, stats: Stats)
    fun getStat(keyDevice: KeyDevice):List<Stats>
}