package ru.sylas.model.tablesDAO.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.exceptions.HellException
import ru.sylas.model.dataclass.GameSize
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.GameT

class GameDao(id : EntityID<Int>):IntEntity(id) {
    companion object : IntEntityClass<GameDao>(GameT)

    var type by GameTypeDao referencedOn GameT.type
    var image by GameT.image
    var size by GameSizeDao referencedOn GameT.size
}

fun List<GameDao>.toGameList() =
    this.map {
        it.toGamesResponse()
    }


fun GameDao.toGamesResponse() = GamesResponse (
        type = getType(this.type.type),
        image = this.image,
        size = getSize(this.size.size)
        )

fun getSize(size: String): GameSize {
    return when(size){
        "Small"-> GameSize.Small
        "Medium"->GameSize.Medium
        "Large"->GameSize.Large
        else -> throw HellException("Unknown Size")
    }
}

fun getType(type:String):GameType{
    return when(type){
        "Numbers"-> GameType.Numbers
        "Animals" -> GameType.Animals
        else -> throw HellException("Unknown Type")
    }
}