package ru.sylas.model.tablesDAO

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.UserTable
import java.util.*

class UserTableDao(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserTableDao>(UserTable)

    var login by UserTable.login
    var secret by UserTable.secret
    var firstName by UserTable.firstName
    var avatar by UserTable.avatar

}