package ru.sylas.service.authorizationservice

import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.requestdataclasses.PinCode

interface AuthorizationService {
    fun authorization(user: AuthUser,keyDevice: KeyDevice) : UserToken
    fun pinAuthorization(pin: PinCode,keyDevice: KeyDevice) : UserToken
    fun registration(user: NewUser,keyDevice: KeyDevice): UserToken

}