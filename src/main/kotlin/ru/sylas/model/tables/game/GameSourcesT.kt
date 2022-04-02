package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object GameSourcesT: Table("gameSources") {
    val typeId = reference("type",GameTypeT)
    val type = reference("typeSource",SourceTypeT)
    val sourceId = reference("source",SourceT)
    override val primaryKey = PrimaryKey(typeId, sourceId, name = "gameSo_PK")
}