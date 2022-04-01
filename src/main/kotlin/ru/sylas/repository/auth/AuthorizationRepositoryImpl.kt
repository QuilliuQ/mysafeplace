package ru.sylas.repository.auth

import ru.sylas.common.Hashing.sha256
import ru.sylas.common.JWTConfig
import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.common.upsert
import ru.sylas.exceptions.BadCredentialsException
import ru.sylas.exceptions.BadKeyDeviceException
import ru.sylas.exceptions.BadUserKeyDeviceException
import ru.sylas.exceptions.UserAlreadyCreatedException
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.auth.Token
import ru.sylas.model.tables.auth.UserKeyDevice
import ru.sylas.model.tables.auth.UserTable
import ru.sylas.model.tablesDAO.app.KeyDeviceDao
import ru.sylas.model.tablesDAO.auth.TokenDao
import ru.sylas.model.tablesDAO.auth.UserKeyDeviceDao
import ru.sylas.model.tablesDAO.auth.UserTableDao
import ru.sylas.model.tablesDAO.auth.toUserToken
import ru.sylas.model.tablesDAO.pincode.PinCodeDao
import ru.sylas.model.tablesDAO.pincode.generatePinCode
import ru.sylas.model.tablesDAO.pincode.toPinCode
import kotlin.random.Random

class AuthorizationRepositoryImpl: AuthorizationRepository {

    override fun authorization(user: AuthUser, keyDevice: KeyDevice): UserToken {
        return loggedTransaction{
            val userDB = UserTableDao.find { UserTable.email eq user.email }.firstOrNull().guard {
                throw BadCredentialsException()
            }
            if (userDB.secret == user.password.sha256()) {
                val keyDeviceDB =
                    KeyDeviceDao.find { KeyDeviceT.keyDevice eq keyDevice.keyDevice }.firstOrNull().guard {
                        throw BadKeyDeviceException()
                    }

                val userKey = UserKeyDeviceDao.upsert(UserKeyDevice.keyDeviceId, keyDeviceDB.id) {
                    this.userId = userDB
                    this.keyDeviceId = keyDeviceDB
                }

                TokenDao.upsert(Token.userId, userDB.id, Token.userKeyDeviceId, userKey.id) {
                    this.userId = userDB
                    this.accessToken = JWTConfig.generateToken(userDB.secret)
                    this.userKeyDeviceId = userKey
                }.toUserToken()
            } else {
                throw BadCredentialsException()
            }
        }
    }



    override fun registration(user: NewUser, keyDevice: KeyDevice): UserToken {
        return loggedTransaction {
            if(UserTableDao.find { UserTable.email eq user.email }.empty()){
              val keyDeviceDB =  KeyDeviceDao.find { KeyDeviceT.keyDevice eq keyDevice.keyDevice }.firstOrNull().guard {
                    throw BadKeyDeviceException()
                }

              val userDB = UserTableDao.new {
                  this.email = user.email
                  this.secret = user.password.sha256()
                  this.childrenName = user.childrenName
                  this.parentName = user.parentName
              }
                val userKey = UserKeyDeviceDao.new {
                    this.userId = userDB
                    this.keyDeviceId = keyDeviceDB
                }

                TokenDao.new {
                    this.userId = userDB
                    this.accessToken = JWTConfig.generateToken(userDB.secret)
                    this.userKeyDeviceId = userKey
                }.toUserToken()
            }
            else throw UserAlreadyCreatedException()
        }
    }









    override fun pinAuthorization(pin: PinCode, keyDevice: KeyDevice): UserToken {
        TODO("Not yet implemented")
    }

    override fun genPinCode(keyDevice: KeyDevice): PinCode {
        return loggedTransaction {
            val keyDeviceDB =
                KeyDeviceDao.find { KeyDeviceT.keyDevice eq keyDevice.keyDevice }.firstOrNull().guard {
                    throw BadKeyDeviceException()
                }
            val userKeyDeviceDB = UserKeyDeviceDao.find{ UserKeyDevice.keyDeviceId eq keyDeviceDB.id}.firstOrNull().guard {
                //данное устройство не авторизвано ниодном пользователем
                throw BadUserKeyDeviceException()
            }
            PinCodeDao.new {
                this.pinCode = generatePinCode()
                this.userKeyDeviceId = userKeyDeviceDB
            }.toPinCode()
        }
    }
}



