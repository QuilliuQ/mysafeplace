package ru.sylas.service

import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.repository.AuthorizationRepository

class AuthorizationServiceImpl(private val storage: AuthorizationRepository): AuthorizationService {
    override fun authorization(user: User): Resource<UserToken> {
        return storage.auth(user)
    }

    override fun registration(user: User): Resource<UserToken> {
        return storage.reg(user)
    }

}