package ru.sylas.model.tablesDAO.auth

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.tables.auth.Token
import java.util.*

class TokenDao(id: EntityID<UUID>): UUIDEntity(id){
    companion object : UUIDEntityClass<TokenDao>(Token)

    var userId by UserTableDao referencedOn Token.userId
    var accessToken by Token.accessToken
    var userKeyDeviceId by UserKeyDeviceDao referencedOn Token.userKeyDeviceId
}

fun TokenDao.toUserToken() =
    UserToken(
        userId = this.userId.id.value,
        accessToken = this.accessToken
    )
