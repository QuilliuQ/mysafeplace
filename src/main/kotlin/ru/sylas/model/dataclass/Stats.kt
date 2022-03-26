package ru.sylas.model.dataclass

import ru.sylas.model.requestdataclasses.GameType

data class Stats(
    val type:GameType,
    val errors:Int,
    val timeInGame: Int
)