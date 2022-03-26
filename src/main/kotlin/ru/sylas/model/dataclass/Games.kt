package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response
import ru.sylas.model.requestdataclasses.GameType


@Response
sealed class Games(
){
    data class NumberGame(val type: GameType, val sounds: List<Correspond>,val sources: List<Correspond>):Games()
    data class AnimalGame(val type: GameType, val sounds: List<Correspond>,val unblock:List<Animals>):Games()
    data class HouseGame(val type: GameType, val sources: List<Corresponding>):Games()
    data class CarsGame(val type: GameType, val sources: List<Corresponding>):Games()
}


data class Corresponding(
    val a : Correspond,
    val b : Correspond
)
data class Correspond(
    val source:String,
    val id: String
)
