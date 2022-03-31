package ru.sylas.model.tablesDAO.auth

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.auth.UserTable
import java.util.*

class UserTableDao(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserTableDao>(UserTable)

    var email by UserTable.email
    var secret by UserTable.secret
    var childrenName by UserTable.childrenName
    var parentName by UserTable.parentName

}