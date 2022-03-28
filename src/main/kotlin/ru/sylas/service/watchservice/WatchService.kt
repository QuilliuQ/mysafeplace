package ru.sylas.service.watchservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.Stats
import ru.sylas.model.requestdataclasses.PinCode

interface WatchService {

    fun getDevices():List<PhoneUser>
    fun getPinCode(key: KeyDevice):PinCode
    fun getStats(key:KeyDevice):List<Stats>
}