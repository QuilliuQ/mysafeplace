package ru.sylas.model.tablesDAO.pincode

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.auth.UserKeyDevice
import ru.sylas.model.tables.pincode.PinCodeT
import ru.sylas.model.tablesDAO.auth.UserKeyDeviceDao
import java.util.UUID

class PinCodeDao(id :EntityID<UUID>):UUIDEntity(id) {
    companion object : UUIDEntityClass<PinCodeDao>(PinCodeT)

    var pinCode by PinCodeT.pinCode
    var userKeyDeviceId by UserKeyDeviceDao referencedOn PinCodeT.userKeyDeviceId
}