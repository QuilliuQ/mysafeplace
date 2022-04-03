package ru.sylas.service.watchservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.dataclass.WatchStats
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.repository.watch.WatchRepository

class WatchServiceImpl(private val repository: WatchRepository):WatchService {
    override fun getDevices(): List<PhoneUser> {
       return repository.getDevices()
    }

    override fun getPinCode(key: KeyDevice): PinCode {
        return repository.getPinCode(key)
    }


    override fun getStats(key: KeyDevice): List<WatchStats> {
        return repository.getStats(key)
    }
}