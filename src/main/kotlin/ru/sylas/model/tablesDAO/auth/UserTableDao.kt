package ru.sylas.model.tablesDAO.auth

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.auth.UserTableT
import java.util.*

class UserTableDao(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserTableDao>(UserTableT)

    var email by UserTableT.email
    var secret by UserTableT.secret
    var childrenName by UserTableT.childrenName
    var parentName by UserTableT.parentName

}