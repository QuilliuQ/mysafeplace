package ru.sylas.model.dataclass

import ru.sylas.model.requestdataclasses.GameType

data class WatchStats(
    val type: String,
    val percent:Int
)