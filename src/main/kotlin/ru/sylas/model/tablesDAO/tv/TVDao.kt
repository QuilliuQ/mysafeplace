package ru.sylas.model.tablesDAO.tv

import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.tv.TV

class TVDao(id:EntityID<Int>):IntEntity(id) {
    companion object: IntEntityClass<TVDao>(TV)
    var deviceId by TV.deviceId
    var device by TV.device
    var playerName by TV.playerName
}