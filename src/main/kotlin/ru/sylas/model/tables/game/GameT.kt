package ru.sylas.model.tables.game

import org.jetbrains.exposed.dao.id.IntIdTable

object GameT:IntIdTable("game") {
    val type =reference("type",GameTypeT)
    val image = text("image")
    val size = reference("size",SizeGameImage)
}

