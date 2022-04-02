package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object OneGameT:IntIdTable("oneGame") {
    val corresponding = reference("cor",CorrespondingT)
    val type = reference("type",GameTypeT)

}