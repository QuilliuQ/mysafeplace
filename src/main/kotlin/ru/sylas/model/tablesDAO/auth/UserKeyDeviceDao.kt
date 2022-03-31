package ru.sylas.model.tablesDAO.auth

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.auth.UserKeyDevice
import ru.sylas.model.tablesDAO.app.KeyDeviceDao

class UserKeyDeviceDao(id:EntityID<Int>):IntEntity(id) {
    companion object: IntEntityClass<UserKeyDeviceDao>(UserKeyDevice)

    var keyDeviceId by KeyDeviceDao referencedOn UserKeyDevice.keyDeviceId
    var userId by UserTableDao referencedOn UserKeyDevice.userId
}