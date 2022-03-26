package ru.sylas.service

import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken

interface AuthorizationService {
    fun authorization(user: User) : Resource<UserToken>

    fun registration(user: User): Resource<UserToken>

}