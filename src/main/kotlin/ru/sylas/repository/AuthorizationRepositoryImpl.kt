package ru.sylas.repository

import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.exceptions.UserAlreadyCreated
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.tables.UserTable
import ru.sylas.model.tablesDAO.UserTableDao

class AuthorizationRepositoryImpl:AuthorizationRepository {
    override fun auth(user: AuthUser): UserToken {
      throw UserAlreadyCreated()
    }

    override fun reg(user: NewUser): UserToken {
              transaction {
                    UserTableDao.find { UserTable.login eq user.email }.singleOrNull().let{
                        throw UserAlreadyCreated()
                    }


                }
            }
        }







//
//UserTableDao.find{ UserTable.login eq user.login}.singleOrNull().also {
//    response = AuthorizationResponse(error = "Имя пользователя занято", statusCode = HttpStatusCode.BadRequest)
//}.guard {
//    UserTableDao.new {
//        login = user.login
//        secret = user.password.sha256()
//        firstName = user.firstName!!
//    }.also {
//        val userToken = JWTConfig.generateToken(it.secret)
//        TokenDao.new(it.id.value) {
//            accessToken = userToken
//        }.also { tokenDao ->
//            response = AuthorizationResponse(data =
//            UserToken(
//                userId = tokenDao.id.toString(),
//                accessToken = tokenDao.accessToken),
//                statusCode = HttpStatusCode.OK
//            )
//        }
//
//    }
//    return@transaction
//}
//}