package ru.sylas.model.tables.pincode

import org.jetbrains.exposed.dao.id.UUIDTable
import ru.sylas.model.tables.auth.UserKeyDevice

object PinCodeT :UUIDTable("pinCode"){
    val pinCode = integer("pinCode")
    val userKeyDeviceId = reference("userKeyDeviceId",UserKeyDevice)
}