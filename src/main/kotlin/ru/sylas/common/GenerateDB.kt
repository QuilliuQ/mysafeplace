package ru.sylas.common

import ru.sylas.model.dataclass.Animals
import ru.sylas.model.dataclass.GameSize
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.Sounds
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.AnimalT
import ru.sylas.model.tables.game.GameSizeT
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tables.game.SoundsT
import ru.sylas.model.tables.watch.WatchKeysT
import ru.sylas.model.tablesDAO.game.AnimalDao
import ru.sylas.model.tablesDAO.game.GameTypeDao
import ru.sylas.model.tablesDAO.game.GameSizeDao
import ru.sylas.model.tablesDAO.game.SoundsDao
import ru.sylas.model.tablesDAO.watch.WatchKeysDao

fun generateDBConstants() {
    generateGameType()
    generateGameSize()
    generateAnimals()
    generateWatchKeys()
//    generateAnimalSourrce()
}

fun generateAnimalSource() {
   val sounds = listOf(
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
        )
    ).map {
       SoundsDao.upsert(SoundsT.sourceId,it.id){
           path = it.source
           sourceId = it.id
       }
   }


//    AnimalsDao.upsert()
}

fun generateWatchKeys() {
    listOf(
        PhoneUser(
            name = "Vitaliy Xiaomi",
            keyDevice = "asdq#4sd54dsw"
        ),PhoneUser(
            name = "Nina Honor",
            keyDevice = "cjii!78cjii!78asdq#4sd54dsw"
        ),PhoneUser(
            name = "Aleksei Samsung",
            keyDevice = "asdq#4sd54d#4sd54dsw"
        ),PhoneUser(
            name = "Olga Xiaomi",
            keyDevice = "cjii!78asdq#4sd54dsw"
        ),PhoneUser(
            name = "Vitaliy Iphone",
            keyDevice = "aaxzvgmi33sdq#4sd54dsw"
        ),PhoneUser(
            name = "Vitaliy Meizu",
            keyDevice = "asdq#4sd54bvxcjii!78dsw"
        )
    ).map {
        WatchKeysDao.upsert(WatchKeysT.name,it.name){
            this.keyDevice =it.keyDevice
            this.name = it.name
        }
    }

}

private fun generateAnimals() {
    Animals.values().map {
        AnimalDao.upsert(AnimalT.name, it.name) {
            name = it.name
        }
    }
}

private fun generateGameSize() {
    GameSize.values().map {
        GameSizeDao.upsert(GameSizeT.size, it.name) {
            size = it.name
        }
    }
}

private fun generateGameType() {
    GameType.values().map {
        GameTypeDao.upsert(GameTypeT.type, it.name) {
            type = it.name
        }
    }
}