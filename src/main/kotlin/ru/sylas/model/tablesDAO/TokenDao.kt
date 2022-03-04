package ru.sylas.model.tablesDAO

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.tables.Token
import java.util.*

class TokenDao(id: EntityID<UUID>): UUIDEntity(id){
    companion object : UUIDEntityClass<TokenDao>(Token)

    var accessToken by Token.accessToken
    var refreshToken by Token.refreshToken
    var expireAt by Token.expireAt
}