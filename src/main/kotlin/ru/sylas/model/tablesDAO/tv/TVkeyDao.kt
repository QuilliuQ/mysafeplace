package ru.sylas.model.tablesDAO.tv

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.tv.TVkey
import ru.sylas.model.tablesDAO.app.MobileDao

class TVkeyDao(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<TVkeyDao>(TVkey)

    var tvId by TVDao referencedOn TVkey.tvId
    var keyDevice by TVkey.keyDevice
}

fun TVkeyDao.toKey()= KeyDevice(this.keyDevice)