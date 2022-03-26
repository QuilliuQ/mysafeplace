package ru.sylas.repository.dao

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