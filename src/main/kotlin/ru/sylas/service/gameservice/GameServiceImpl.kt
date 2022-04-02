package ru.sylas.service.gameservice


import ru.sylas.model.dataclass.Games
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.repository.game.GameRepository

class GameServiceImpl(private val repository: GameRepository):GameService {
    override fun getGames(): List<GamesResponse> {
        return repository.getGames()
    }

    override fun getGame(id: GameType): Games {
        return repository.getGame(id)
    }
}