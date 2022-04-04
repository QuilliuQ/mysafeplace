package ru.sylas.routings




import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Utils.auth
import ru.sylas.common.myApiRouting
import ru.sylas.common.Tag
import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.GameID
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.animals.AninalGameT.sounds
import ru.sylas.repository.game.genAni
import ru.sylas.service.gameservice.GameService


fun Application.gameRouting(){

    val service: GameService by inject()

    myApiRouting {
        tag(Tag.Games){
               auth{
        route("/games") {
                   this.get<Unit, List<GamesResponse>, AuthUser>(
                       info(
                           summary = "Получение списка игр"
                       ),
                       example = listOf(GamesResponse(GameType.Numbers,"image.png",GameSize.Small))
                   ) {
                       respond(service.getGames())
                   }
               }
                   route("/game") {
                   this.get<GameID, Games, AuthUser>(
                       info(
                           summary = "Получение конкретной игры"
                       ),
                       example = Games.NumberGame(
                           GameType.Numbers,
                           listOf(Sounds("one.mp3", "1")),
                           listOf(Source("one.png", "1"))
                       )
                   ) {id->

                       respond(when(id.name){
                           GameType.Numbers -> getNumbers()
                           GameType.Animals -> genAnimals()

                       })
                   }
               }

           }

        }
        }
    }





fun getNumbers():Games{
    val sound = listOf(

        Sounds(
            source = "sounds/numbers/zero.mp3",
            id = "zero"
        ),
        Sounds(
            source = "sounds/numbers/one.mp3",
            id = "one"
        ),
        Sounds(
            source = "sounds/numbers/two.mp3",
            id = "two"
        ),
        Sounds(
            source = "sounds/numbers/three.mp3",
            id = "three"
        ),
        Sounds(
            source = "sounds/numbers/four.mp3",
            id = "four"
        ),
        Sounds(
            source = "sounds/numbers/five.mp3",
            id = "five"
        ),
        Sounds(
            source = "sounds/numbers/six.mp3",
            id = "six"
        ),
        Sounds(
            source = "sounds/numbers/seven.mp3",
            id = "seven"
        ),
        Sounds(
            source = "sounds/numbers/eight.mp3",
            id = "eight"
        ),
        Sounds(
            source = "sounds/numbers/nine.mp3",
            id = "nine"
        ))

    val list = listOf(

        Source(
            source = "images/numbers/0.png",
            id = "zero"
        ),
        Source(
            source = "images/numbers/1.png",
            id = "one"
        ),
        Source(
            source = "images/numbers/2.png",
            id = "two"
        ),
        Source(
            source = "images/numbers/3.png",
            id = "three"
        ),
        Source(
            source = "images/numbers/4.png",
            id = "four"
        ),
        Source(
            source = "images/numbers/5.png",
            id = "five"
        ),
        Source(
            source = "images/numbers/6.png",
            id = "six"
        ),
        Source(
            source = "images/numbers/7.png",
            id = "seven"
        ),
        Source(
            source = "images/numbers/8.png",
            id = "eight"
        ),
        Source(
            source = "images/numbers/9.png",
            id = "nine"
        ),
    )
    return Games.NumberGame(
        type = GameType.Numbers,
        sounds =sound,
        sources = list
    )
}


fun genAnimals():Games {
    val sound = listOf(
        Sounds(
            source = "sounds/animals/cow.mp3",
            id = "cow"
        ),Sounds(
            source = "sounds/animals/chicken.mp3",
            id = "chicken"
        ),Sounds(
            source = "sounds/animals/cat.mp3",
            id = "cat"
        ),Sounds(
            source = "sounds/animals/dog.mp3",
            id = "dog"
        ),Sounds(
            source = "sounds/animals/frog.mp3",
            id = "frog"
        ),Sounds(
            source = "sounds/animals/pig.mp3",
            id = "pig"
        ),Sounds(
            source = "sounds/animals/goat.mp3",
            id = "goat"
        ),Sounds(
            source = "sounds/animals/donkey.mp3",
            id = "donkey"
        ))

    val list = listOf(

        Source(
            source = "images/animals/cow.svg",
            id = "cow"
        ),
        Source(
            source = "images/animals/cat.svg",
            id = "cat"
        ),
        Source(
            source = "images/animals/dog.svg",
            id = "dog"
        ),
        Source(
            source = "images/animals/frog.svg",
            id = "frog"
        ),
        Source(
            source = "images/animals/pig.svg",
            id = "pig"
        ),
        Source(
            source = "images/animals/duck.svg",
            id = "duck"
        ),
        Source(
            source = "images/animals/donkey.svg",
            id = "donkey"
        ),
        Source(
            source = "images/animals/goat.svg",
            id = "goat"
        ),
        Source(
            source = "images/animals/chicken.svg",
            id = "chicken"
        ),
    )
    return Games.AnimalGame(
        type = GameType.Animals,
        sounds = sound,
        sources = list,
        unblock = genAni()
    )

}
