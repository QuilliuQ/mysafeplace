package ru.sylas.repository

import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.JWTConfig
import ru.sylas.common.Resource
import ru.sylas.common.Utils.guard
import ru.sylas.model.dataclass.ResponseError
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.requestdataclasses.AuthUser
import ru.sylas.model.requestdataclasses.NewUser
import ru.sylas.model.tables.UserTable
import ru.sylas.model.tablesDAO.TokenDao
import ru.sylas.model.tablesDAO.UserTableDao
import java.util.*

class AuthorizationRepositoryImpl:AuthorizationRepository {
    override fun auth(user: AuthUser): UserToken {
      return UserToken(UUID.randomUUID(),"")
    }

    override suspend fun reg(user: NewUser): UserToken {
              return  transaction {
                    UserTableDao.find { UserTable.login eq user.email }.singleOrNull().let{
                        throw UserAlreadyCreated()
                    }


                }
            }
        }




class UserAlreadyCreated():Exception("Имя пользователя занято")


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