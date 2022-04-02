package ru.sylas.repository.game


import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.HellException
import ru.sylas.model.dataclass.Games
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tablesDAO.game.GameDao
import ru.sylas.model.tablesDAO.game.toGameList

class GameRepositoryImpl:GameRepository {
    override fun getGames(): List<GamesResponse> {
        return loggedTransaction {
            GameDao.all().toList().toGameList()
        }
    }

    override fun getGame(id: GameType): Games {
        when(id){
            GameType.Numbers -> TODO()
            GameType.Animals -> TODO()
            GameType.House -> TODO()
            GameType.Cars -> TODO()
        }
    }
}