package ru.sylas.model.tables.stats

import org.jetbrains.exposed.dao.id.IntIdTable
import ru.sylas.model.tables.game.GameTypeT

object UserStatT : IntIdTable("userStat") {
    val type = reference("gameType",GameTypeT)
    val errors = integer("errors")
    val timeInGame = integer("timeInGame")
}
