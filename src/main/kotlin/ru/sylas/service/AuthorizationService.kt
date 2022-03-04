package ru.sylas.service

import ru.sylas.model.dataclass.RefreshToken
import ru.sylas.model.responses.AuthorizationResponse
import ru.sylas.model.responses.DefaultResponse
import ru.sylas.model.dataclass.User

interface AuthorizationService {
    fun authorization(user: User) : AuthorizationResponse

    fun registration(user: User): AuthorizationResponse

    fun refreshToken(refreshToken: RefreshToken) : AuthorizationResponse
}