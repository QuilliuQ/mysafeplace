package ru.sylas.repository.auth

import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.Utils
import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.BadKeyDeviceException
import ru.sylas.exceptions.UserAlreadyCreatedException
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.auth.UserTable
import ru.sylas.model.tablesDAO.app.KeyDeviceDao
import ru.sylas.model.tablesDAO.auth.TokenDao
import ru.sylas.model.tablesDAO.auth.UserKeyDeviceDao
import ru.sylas.model.tablesDAO.auth.UserTableDao

class AuthorizationRepositoryImpl: AuthorizationRepository {

    override fun authorization(user: AuthUser, keyDevice: KeyDevice): UserToken {
        TODO("Not yet implemented")
    }



    override fun registration(user: NewUser, keyDevice: KeyDevice): UserToken {
        loggedTransaction {

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
                UserKeyDeviceDao.new {
                    this.userId = userDB
                    this.keyDeviceId = keyDeviceDB
                }

                TokenDao.new {
                    this.userId
                    this.accessToken
                    this.userKeyDeviceId
                }
            }
            else throw UserAlreadyCreatedException()
        }
    }









    override fun pinAuthorization(pin: PinCode, keyDevice: KeyDevice): UserToken {
        TODO("Not yet implemented")
    }

    override fun genPinCode(keyDevice: KeyDevice): PinCode {
        TODO("Not yet implemented")
    }
}


