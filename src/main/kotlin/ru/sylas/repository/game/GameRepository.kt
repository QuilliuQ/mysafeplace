package ru.sylas.repository.game

import ru.sylas.model.dataclass.Games
import ru.sylas.model.dataclass.GamesResponse
import ru.sylas.model.requestdataclasses.GameType

interface GameRepository {
    fun getGames():List<GamesResponse>
    fun getGame(id: GameType): Games
}