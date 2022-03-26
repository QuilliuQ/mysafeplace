package ru.sylas.service

import kotlinx.coroutines.flow.Flow
import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser

interface AuthorizationService {
    fun authorization(user: AuthUser) : Flow<Resource<UserToken>>
    suspend fun registration(user: NewUser): Flow<Resource<UserToken>>

}