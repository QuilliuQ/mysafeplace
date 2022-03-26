package ru.sylas.repository.dao

import ru.sylas.common.Hashing.sha256
import ru.sylas.common.JWTConfig
import ru.sylas.common.Resource
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.tablesDAO.TokenDao
import ru.sylas.model.tablesDAO.UserTableDao
//
//class AuthDao {
//    suspend fun regUser(user: User):UserToken{
//       return UserTableDao.new {
//            login = user.login
//            secret = user.password.sha256()
//            firstName = user.firstName!!
//        }.also {
//            val userToken = JWTConfig.generateToken(it.secret)
//            TokenDao.new(it.id.value) {
//                accessToken = userToken
//            }.also { tokenDao ->
//                    UserToken(
//                        userId = tokenDao.id.toString(),
//                        accessToken = tokenDao.accessToken
//                    )
//
//            }
//        }
//    }
//}