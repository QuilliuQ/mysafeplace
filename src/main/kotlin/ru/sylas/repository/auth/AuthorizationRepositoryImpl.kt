package ru.sylas.repository.auth

import org.jetbrains.exposed.sql.and
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.JWTConfig
import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.common.upsert
import ru.sylas.exceptions.*
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.auth.TokenT
import ru.sylas.model.tables.auth.UserKeyDeviceT
import ru.sylas.model.tables.auth.UserTableT
import ru.sylas.model.tables.pincode.PinCodeT
import ru.sylas.model.tablesDAO.app.KeyDeviceDao
import ru.sylas.model.tablesDAO.auth.TokenDao
import ru.sylas.model.tablesDAO.auth.UserKeyDeviceDao
import ru.sylas.model.tablesDAO.auth.UserTableDao
import ru.sylas.model.tablesDAO.auth.toUserToken
import ru.sylas.model.tablesDAO.pincode.PinCodeDao
import ru.sylas.model.tablesDAO.pincode.generatePinCode
import ru.sylas.model.tablesDAO.pincode.toPinCode

class AuthorizationRepositoryImpl: AuthorizationRepository {

    override fun authorization(user: AuthUser, keyDevice: KeyDevice): UserToken {
        return loggedTransaction{
            val userDB = UserTableDao.find { UserTableT.email eq user.email }.firstOrNull().guard {
                throw BadCredentialsException()
            }
            if (userDB.secret == user.password.sha256()) {
                val keyDeviceDB =
                    KeyDeviceDao.find { KeyDeviceT.keyDevice eq keyDevice.keyDevice }.firstOrNull().guard {
                        throw BadKeyDeviceException()
                    }

                val userKey = UserKeyDeviceDao.upsert(UserKeyDeviceT.keyDeviceId, keyDeviceDB.id) {
                    this.userId = userDB
                    this.keyDeviceId = keyDeviceDB
                }

                TokenDao.upsert(TokenT.userId, userDB.id, TokenT.userKeyDeviceId, userKey.id) {
                    this.userId = userDB
                    this.accessToken = JWTConfig.generateToken(userDB)
                    this.userKeyDeviceId = userKey
                }.toUserToken()
            } else {
                throw BadCredentialsException()
            }
        }
    }



    override fun registration(user: NewUser, keyDevice: KeyDevice): UserToken {
        return loggedTransaction {
            if(UserTableDao.find { UserTableT.email eq user.email }.empty()){
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
                    this.accessToken = JWTConfig.generateToken(userDB)
                    this.userKeyDeviceId = userKey
                }.toUserToken()
            }
            else throw UserAlreadyCreatedException()
        }
    }









    override fun pinAuthorization(pin: PinCode, keyDevice: KeyDevice): UserToken {
         return loggedTransaction{
            val useKeyDeviceDB  =  getUserKeyDevice(keyDevice)
            val pinDB = PinCodeDao.find { PinCodeT.pinCode eq pin.pinCode and (PinCodeT.userKeyDeviceId eq useKeyDeviceDB.id) }
                .firstOrNull().guard {
                    throw BadPinCodeException()
                }
             pinDB.delete()
             TokenDao.find{ TokenT.userKeyDeviceId eq useKeyDeviceDB.id }.first().toUserToken()
        }
    }

    override fun genPinCode(keyDevice: KeyDevice): PinCode {
        return loggedTransaction {
            val userKeyDeviceDB = getUserKeyDevice(keyDevice)
            PinCodeDao.new {
                this.pinCode = generatePinCode()
                this.userKeyDeviceId = userKeyDeviceDB
            }.toPinCode()
        }
    }
}

fun getKeyDevice(keyDevice:KeyDevice) = KeyDeviceDao
    .find { KeyDeviceT.keyDevice eq keyDevice.keyDevice }
    .firstOrNull()
    .guard {
    throw BadKeyDeviceException()
}

fun getUserKeyDevice(keyDevice: KeyDevice) :UserKeyDeviceDao {
    val keyDeviceDB = getKeyDevice(keyDevice)
    return UserKeyDeviceDao.find{ UserKeyDeviceT.keyDeviceId eq keyDeviceDB.id}.firstOrNull().guard {
        //данное устройство не авторизвано ниодном пользователем
        throw BadUserKeyDeviceException()
    }
}