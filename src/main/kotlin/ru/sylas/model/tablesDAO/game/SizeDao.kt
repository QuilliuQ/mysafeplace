package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.game.GameT
import ru.sylas.model.tables.game.SizeGameImage

class SizeDao(id : EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<SizeDao>(SizeGameImage)

    var size by SizeGameImage.size
}