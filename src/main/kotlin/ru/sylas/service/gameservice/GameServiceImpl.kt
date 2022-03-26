package ru.sylas.service.gameservice


import ru.sylas.model.dataclass.Games
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.requestdataclasses.GameType

class GameServiceImpl:GameService {
    override fun getGames(): List<GamesResponse> {
        return emptyList()
    }

    override fun getGame(id: GameType): Games {
        return Games.NumberGame(GameType.Numbers, emptyList(), emptyList())
    }
}