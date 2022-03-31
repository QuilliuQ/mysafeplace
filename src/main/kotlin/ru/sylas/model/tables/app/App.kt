package ru.sylas.model.tables.app

import org.jetbrains.exposed.dao.id.IntIdTable

object App : IntIdTable("app") {
    val appId = text("appId")
    val competitor = text("competitor")
}





