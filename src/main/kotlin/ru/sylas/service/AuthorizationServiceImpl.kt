package ru.sylas.service

import ru.sylas.model.dataclass.RefreshToken
import ru.sylas.model.responses.AuthorizationResponse
import ru.sylas.model.responses.DefaultResponse
import ru.sylas.model.dataclass.User
import ru.sylas.repository.AuthorizationRepository

class AuthorizationServiceImpl(private val storage: AuthorizationRepository): AuthorizationService {
    override fun authorization(user: User): AuthorizationResponse {
        return storage.auth(user)
    }

    override fun registration(user: User): AuthorizationResponse {
        return storage.reg(user)
    }

    override fun refreshToken(refreshToken: RefreshToken): AuthorizationResponse {
       return storage.refreshToken(refreshToken)
    }
}