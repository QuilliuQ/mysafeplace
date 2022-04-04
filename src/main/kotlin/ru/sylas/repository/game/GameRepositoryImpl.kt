package ru.sylas.repository.game


import io.ktor.features.*
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.BadKeyDeviceException
import ru.sylas.exceptions.HellException
import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.SoundsT
import ru.sylas.model.tablesDAO.game.GameDao
import ru.sylas.model.tablesDAO.game.SoundsDao
import ru.sylas.model.tablesDAO.game.animal.AnimalsDao
import ru.sylas.model.tablesDAO.game.toGameList
import kotlin.random.Random

class GameRepositoryImpl:GameRepository {
    override fun getGames(): List<GamesResponse> {
        return loggedTransaction {
            GameDao.all().toList().toGameList()
        }
    }

    override fun getGame(id: GameType): Games {
       throw BadKeyDeviceException()
       }
    }

//
//private fun AnimalsDao.toGames() :Games{
//    return Games.AnimalGame(
//        type = GameType.Animals,
//        sounds = getSounds(this.sounds),
//        unblock = genAni()
//    )
//}
//
fun genAni(): List<AnimalsUn> {
    return Animals.values().map {
        AnimalsUn(
            animal = it,
            block = Random.nextBoolean()
        )
    }
}

//fun getSounds(sounds: SoundsDao): List<Sounds> {
//   return SoundsDao.find { SoundsT.id eq sounds.id }.toList().toSounds()
//}
//
//private fun  List<SoundsDao>.toSounds():List<Sounds> {
//   return this.map {
//        it.toSound()
//    }
//}
//
//private fun SoundsDao.toSound(): Sounds {
//    return Sounds(
//        source = this.path,
//        id = this.sourceId
//    )
//}
