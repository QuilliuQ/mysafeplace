package ru.sylas.service.watchservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.requestdataclasses.PinCode

class WatchServiceImpl:WatchService {
    override fun getDevices(): List<PhoneUser> {
       return emptyList()
    }

    override fun getPinCode(key: KeyDevice): PinCode {
        return PinCode(1)
    }

    override fun getStats(key: KeyDevice): List<Stats> {
        return emptyList()
    }
}