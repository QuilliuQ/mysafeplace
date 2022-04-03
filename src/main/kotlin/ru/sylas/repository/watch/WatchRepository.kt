package ru.sylas.repository.watch

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.dataclass.WatchStats
import ru.sylas.model.requestdataclasses.PinCode

interface WatchRepository {
    fun getDevices():List<PhoneUser>
    fun getPinCode(key: KeyDevice):PinCode
    fun getStats(key: KeyDevice): List<WatchStats>
}