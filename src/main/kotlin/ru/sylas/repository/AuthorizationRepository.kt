package ru.sylas.repository


import kotlinx.coroutines.flow.Flow
import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken

interface AuthorizationRepository {
    fun auth(user: User) : Resource<UserToken>
    suspend fun reg(user: User) : Resource<UserToken>
}