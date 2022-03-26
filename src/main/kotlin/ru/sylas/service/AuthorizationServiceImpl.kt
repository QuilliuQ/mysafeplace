package ru.sylas.service

import kotlinx.coroutines.flow.Flow
import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.repository.AuthorizationRepository

class AuthorizationServiceImpl(private val storage: AuthorizationRepository): AuthorizationService {
    override fun authorization(user: AuthUser): Flow<Resource<UserToken>> {
        return storage.auth(user)
    }

    override suspend fun registration(user: NewUser): Flow<Resource<UserToken>> {
        return storage.reg(user)
    }

}