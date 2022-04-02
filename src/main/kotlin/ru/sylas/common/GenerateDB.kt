package ru.sylas.common

import ru.sylas.model.dataclass.Animals
import ru.sylas.model.dataclass.GameSize
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.AnimalT
import ru.sylas.model.tables.game.GameSizeT
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tablesDAO.game.AnimalDao
import ru.sylas.model.tablesDAO.game.GameTypeDao
import ru.sylas.model.tablesDAO.game.GameSizeDao

fun generateDBConstants() {
    generateGameType()
    generateGameSize()
    generateAnimals()
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