package ru.sylas.routings




import com.papsign.ktor.openapigen.route.*
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.response.respond
import io.ktor.application.*
import io.ktor.http.*
import org.koin.ktor.ext.inject
import ru.sylas.common.Utils.auth
import ru.sylas.common.myApiRouting
import ru.sylas.common.Tag
import ru.sylas.exceptions.ForbiddenException
import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.GameID
import ru.sylas.model.requestdataclasses.GameType
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
                       example = listOf(GamesResponse(GameType.Numbers,"image.png",GameSize.Small,false))
                   ) {
                       respond(genMenu())

                   }
               }
                   route("/game") {
                       throws(HttpStatusCode.Forbidden.description("данная игра вам недоступна"),
                           example = ErrorEx("Данная игра вам недоступна"),
                           ForbiddenException::class
                       ).get<GameID, Games, AuthUser>(
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
                           GameType.House -> genHouse()
                           GameType.Cars -> genCars()
                           GameType.Bees -> genBees()
                           else -> throw ForbiddenException("Данная игра вам недоступна")
                       })
                   }
               }

           }

        }
        }
    }

fun genBees(): Games {
 val listc = listOf(

     Source(
     "images/bees/one.png",
     "one"
 ),
     Source(
     "images/bees/two.png",
     "two"
 ),
     Source(
     "images/bees/three.png",
     "three"
 ),
     Source(
     "images/bees/four.png",
     "four"
 ),
     Source(
     "images/bees/five.png",
     "five"
 ),
     Source(
     "images/bees/six.png",
     "six"
 ),
     Source(
     "images/bees/seven.png",
     "seven"
 ),
 )
    val lita = listc.shuffled().take(4)
    val corr = CorrespondingB(
        bees = lita,
        petalCount = lita.map {
            getId(it.id)
        }.shuffled()
    )
    return Games.BeesGame(
        type = GameType.Bees,
        correspondings = corr
    )
}

fun getId(id: String) : Int {
    return when(id){
        "one"-> 1
        "two"-> 2
        "three"-> 3
        "four"-> 4
        "five"-> 5
        "six"-> 6
        "seven"-> 7
        else -> 1
    }
}

fun genCars(): Games {
    val listc = listOf(
        Correspond(1,"red"),
        Correspond(2,"purple"),
        Correspond(3,"yellow"),
    )
    val list = CorrespondingC(
        trucks = listc.shuffled(),
        cars = listc.shuffled()
    )
    return Games.CarsGame(
        type = GameType.Cars,
        correspondings = list
    )
}

fun genHouse(): Games {
    val listc = listOf(
        Correspond(1,"circle"),
        Correspond(2,"semicircle"),
        Correspond(3,"triangle"),
        Correspond(4,"square"),
    )
    val list =
        Corresponding(
            figures = listc.shuffled(),
            houses = listc.shuffled()
        )

 return Games.HouseGame(
     type = GameType.House,
        correspondings = list
 )
}

fun genMenu(): List<GamesResponse> {
    return listOf(
        GamesResponse(
            type = GameType.Numbers,
            image = "images/menu/numbers.png",
            size = genSize(),
            locked = false
        ),
        GamesResponse(
            type = GameType.Animals,
            image = "images/menu/animals.png",
            size = genSize(),
            locked = false
        ),           GamesResponse(
            type = GameType.House,
            image = "images/menu/house.png",
            size = genSize(),
            locked = true
        ),        GamesResponse(
            type = GameType.Cars,
            image = "images/menu/cars.png",
            size = genSize(),
            locked = true
        ),        GamesResponse(
            type = GameType.Bees,
            image = "images/menu/bees.png",
            size = genSize(),
            locked = true
        ),
    )

}

fun genSize(): GameSize {
    return GameSize.values().random()
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
