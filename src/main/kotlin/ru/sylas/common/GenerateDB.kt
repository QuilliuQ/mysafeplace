package ru.sylas.common

import ru.sylas.model.dataclass.GameSize
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.tables.game.GameSizeT
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tablesDAO.game.GameTypeDao
import ru.sylas.model.tablesDAO.game.GameSizeDao

fun generateDB() {
    genGameType()
    getGameSize()

}

private fun getGameSize() {
    GameSize.values().map {
        GameSizeDao.upsert(GameSizeT.size, it.name) {
            size = it.name
        }
    }
}

private fun genGameType() {
    GameType.values().map {
        GameTypeDao.upsert(GameTypeT.type, it.name) {
            type = it.name
        }
    }
}