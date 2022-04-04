package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response
import ru.sylas.model.requestdataclasses.GameType


@Response
sealed class Games(
){
    data class NumberGame(val type: GameType, val sounds: List<Sounds>, val sources: List<Source>):Games()
    data class AnimalGame(val type: GameType, val sounds: List<Sounds>, val sources: List<Source>,val unblock:List<AnimalsUn>):Games()
//    data class HouseGame(val type: GameType, val correspondings: List<Corresponding>):Games()
//    data class CarsGame(val type: GameType, val correspondings: List<Corresponding>):Games()
}

data class AnimalsUn(
    val animal:Animals,
    val block : Boolean
)

data class Corresponding(
    val a : Source,
    val b : Source
)
data class Source(
    val source:String,
    val id: String
)
data class Sounds(
    val source:String,
    val id: String
)
