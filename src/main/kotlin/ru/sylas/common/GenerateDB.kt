package ru.sylas.common

import ru.sylas.model.dataclass.Animals
import ru.sylas.model.dataclass.GameSize
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.AnimalT
import ru.sylas.model.tables.game.GameSizeT
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tables.watch.WatchKeysT
import ru.sylas.model.tablesDAO.game.AnimalDao
import ru.sylas.model.tablesDAO.game.GameTypeDao
import ru.sylas.model.tablesDAO.game.GameSizeDao
import ru.sylas.model.tablesDAO.watch.WatchKeysDao

fun generateDBConstants() {
    generateGameType()
    generateGameSize()
    generateAnimals()
    generateWatchKeys()
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