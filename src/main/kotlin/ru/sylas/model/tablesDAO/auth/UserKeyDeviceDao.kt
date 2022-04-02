package ru.sylas.model.tablesDAO.auth

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.auth.UserKeyDeviceT
import ru.sylas.model.tablesDAO.app.KeyDeviceDao

class UserKeyDeviceDao(id:EntityID<Int>):IntEntity(id) {
    companion object: IntEntityClass<UserKeyDeviceDao>(UserKeyDeviceT)

    var keyDeviceId by KeyDeviceDao referencedOn UserKeyDeviceT.keyDeviceId
    var userId by UserTableDao referencedOn UserKeyDeviceT.userId
}