package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.parameters.QueryParam

data class GameID(@QueryParam("Имя конкретной игры") val name: GameType)


enum class GameType {
        Numbers,
        Animals,
        House,
        Cars
}
