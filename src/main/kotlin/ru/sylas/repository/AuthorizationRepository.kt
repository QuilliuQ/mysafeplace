package ru.sylas.repository


import kotlinx.coroutines.flow.Flow
import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser

interface AuthorizationRepository {
    fun auth(user: AuthUser) : UserToken
    suspend fun reg(user: NewUser) : UserToken
}