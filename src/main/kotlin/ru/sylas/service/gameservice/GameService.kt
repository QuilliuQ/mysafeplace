package ru.sylas.service.gameservice

import ru.sylas.model.dataclass.Games
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.requestdataclasses.GameType

interface GameService {
    fun getGames():List<GamesResponse>
    fun getGame(id: GameType):Games
}