package ru.sylas.service.authorizationservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.repository.AuthorizationRepository
import java.util.*

class AuthorizationServiceImpl(private val storage: AuthorizationRepository): AuthorizationService {
    override fun authorization(user: AuthUser, keyDevice: KeyDevice): UserToken {
        return storage.auth(user)
    }

    override fun pinAuthorization(pin: PinCode, keyDevice: KeyDevice): UserToken {
        return UserToken(UUID.randomUUID(),"")
    }

    override fun registration(user: NewUser, keyDevice: KeyDevice): UserToken {
        return storage.reg(user)
    }

}