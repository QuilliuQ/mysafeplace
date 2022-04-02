package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response
import ru.sylas.model.requestdataclasses.GameType

@Response
data class GamesResponse(
    val type: GameType,
    val image:String,
    val size: GameSize
)
