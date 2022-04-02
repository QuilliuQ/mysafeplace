package ru.sylas.model.tablesDAO.pincode

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.model.tables.pincode.PinCodeT
import ru.sylas.model.tablesDAO.auth.UserKeyDeviceDao
import java.util.UUID
import kotlin.random.Random

class PinCodeDao(id :EntityID<UUID>):UUIDEntity(id) {
    companion object : UUIDEntityClass<PinCodeDao>(PinCodeT)

    var pinCode by PinCodeT.pinCode
    var userKeyDeviceId by UserKeyDeviceDao referencedOn PinCodeT.userKeyDeviceId
}


fun PinCodeDao.toPinCode() =
    PinCode(
        pinCode = pinCode
    )

fun PinCodeDao.generatePinCode(): Int {
    return Random.nextInt(1001,9999)
}
