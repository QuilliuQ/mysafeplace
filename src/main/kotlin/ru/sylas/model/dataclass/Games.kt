package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response
import ru.sylas.model.requestdataclasses.GameType


@Response
sealed class Games(
){
    data class NumberGame(val type: GameType, val sounds: List<Source>, val sources: List<Source>):Games()
    data class AnimalGame(val type: GameType, val sounds: List<Source>, val unblock:List<Animals>):Games()
    data class HouseGame(val type: GameType, val correspondings: List<Corresponding>):Games()
    data class CarsGame(val type: GameType, val correspondings: List<Corresponding>):Games()
}


data class Corresponding(
    val a : Source,
    val b : Source
)
data class Source(
    val source:String,
    val id: String
)
