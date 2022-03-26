package ru.sylas.repository


import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser

interface AuthorizationRepository {
    fun auth(user: AuthUser) : UserToken
    fun reg(user: NewUser) : UserToken
}