package ru.sylas.model.tablesDAO.watch

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.tables.watch.WatchKeysT

class WatchKeysDao(id:EntityID<Int>):IntEntity(id) {
    companion object:IntEntityClass<WatchKeysDao>(WatchKeysT)

    var name by WatchKeysT.name
    var keyDevice by WatchKeysT.keyDevice
}

fun WatchKeysDao.toPhoneUser() = PhoneUser(
    name = this.name,
    keyDevice = this.keyDevice
)