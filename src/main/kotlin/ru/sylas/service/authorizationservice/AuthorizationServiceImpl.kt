package ru.sylas.service.authorizationservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.repository.auth.AuthorizationRepository
import java.util.*

class AuthorizationServiceImpl(private val storage: AuthorizationRepository): AuthorizationService {
    override fun authorization(user: AuthUser, keyDevice: KeyDevice): UserToken {
        return storage.authorization(user, keyDevice)
    }

    override fun pinAuthorization(pin: PinCode, keyDevice: KeyDevice): UserToken {
        return storage.pinAuthorization(pin, keyDevice)
    }

    override fun registration(user: NewUser, keyDevice: KeyDevice): UserToken {
        return storage.registration(user, keyDevice)
    }

    override fun genPinCode(keyDevice: KeyDevice): PinCode {
        return storage.genPinCode(keyDevice)
    }

}