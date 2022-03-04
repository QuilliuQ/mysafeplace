package ru.sylas.model.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTable : UUIDTable("users"){
    val login = varchar("login", 128)
    val secret = text("secret")
    val firstName = varchar("firstName",128)
    val avatar = text("avatar").nullable()
}