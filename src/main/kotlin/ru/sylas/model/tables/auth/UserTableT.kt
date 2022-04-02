package ru.sylas.model.tables.auth

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTableT : UUIDTable("users"){
    val email = varchar("email", 128)
    val secret = text("secret")
    val childrenName = varchar("childrenName",128)
    val parentName = varchar("parentName",128)
}