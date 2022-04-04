package ru.sylas.model.tables.game.animals

import org.jetbrains.exposed.dao.id.IntIdTable
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tables.game.SoundsT
import ru.sylas.model.tables.game.SourceT
import ru.sylas.model.tables.game.SourceTypeT

object AninalGameT:IntIdTable("animals") {
    val type = reference("type",GameTypeT)
    val sounds = reference("source", SoundsT)

}