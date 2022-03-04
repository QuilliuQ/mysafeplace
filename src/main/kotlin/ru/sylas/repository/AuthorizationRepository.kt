package ru.sylas.repository

import ru.sylas.model.dataclass.RefreshToken
import ru.sylas.model.responses.AuthorizationResponse
import ru.sylas.model.responses.DefaultResponse
import ru.sylas.model.dataclass.User

interface AuthorizationRepository {
    fun auth(user: User) : AuthorizationResponse
    fun reg(user: User) : AuthorizationResponse
    fun refreshToken(refreshToken: RefreshToken):AuthorizationResponse
}