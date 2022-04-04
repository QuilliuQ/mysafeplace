package ru.sylas.model.tablesDAO.game.animal

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.dataclass.Games
import ru.sylas.model.tables.game.AnimalT
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tables.game.animals.AninalGameT
import ru.sylas.model.tablesDAO.auth.TokenDao.Companion.referrersOn
import ru.sylas.model.tablesDAO.game.GameTypeDao
import ru.sylas.model.tablesDAO.game.SoundsDao
import ru.sylas.model.tablesDAO.game.SourceDao

class AnimalsDao(id:EntityID<Int>):IntEntity(id) {
    companion object:IntEntityClass<AnimalsDao>(AninalGameT)

    var type by GameTypeDao referencedOn  AninalGameT.type
    var sounds by SoundsDao referencedOn AninalGameT.sounds
}