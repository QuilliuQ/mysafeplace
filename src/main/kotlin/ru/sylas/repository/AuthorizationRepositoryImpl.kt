package ru.sylas.repository

import io.ktor.http.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.JWTConfig
import ru.sylas.common.Utils.guard
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.responses.AuthorizationResponse
import ru.sylas.model.tables.UserTable
import ru.sylas.model.tablesDAO.TokenDao
import ru.sylas.model.tablesDAO.UserTableDao

class AuthorizationRepositoryImpl:AuthorizationRepository {
    override fun auth(user: User): AuthorizationResponse {
        var response = AuthorizationResponse()
        try {
            transaction {
                val userDB = UserTableDao.find{ UserTable.login eq user.login}.firstOrNull().guard{
                    response = AuthorizationResponse(error="Пользователь не найден", statusCode = HttpStatusCode.BadRequest)
                    return@transaction
                }
                if (userDB.secret == user.password.sha256()) {
                    val userToken = JWTConfig.generateToken(user.password.sha256())
                    TokenDao.findById(userDB.id).also { tokenDB ->
                        tokenDB?.accessToken = userToken

                    }.guard {
                        response = AuthorizationResponse(data = UserToken(userDB.id.toString(),
                            accessToken = userToken),
                            statusCode = HttpStatusCode.OK).also {
                            TokenDao.new(userDB.id.value)  {
                                accessToken = userToken
                            }
                        }
                        return@transaction
                    }
                }
                else
                {
                    response = AuthorizationResponse(error = "Неверный пароль", statusCode = HttpStatusCode.BadRequest)
                }


            }
        }
        catch (e:Exception){
            response = AuthorizationResponse(error = e.localizedMessage,statusCode = HttpStatusCode.InternalServerError)
        }
        return response
    }

    override fun reg(user: User): AuthorizationResponse {
        var response = AuthorizationResponse()
        try {
            transaction {
                UserTableDao.find{ UserTable.login eq user.login}.singleOrNull().also {
                    response = AuthorizationResponse(error = "Имя пользователя занято", statusCode = HttpStatusCode.BadRequest)
                }.guard {
                    UserTableDao.new {
                        login = user.login
                        secret = user.password.sha256()
                        firstName = user.firstName!!
                    }.also {
                        val userToken = JWTConfig.generateToken(it.secret)
                        TokenDao.new(it.id.value) {
                            accessToken = userToken
                        }.also { tokenDao ->
                            response = AuthorizationResponse(data =
                            UserToken(
                                userId = tokenDao.id.toString(),
                                accessToken = tokenDao.accessToken),
                                statusCode = HttpStatusCode.OK
                            )
                        }

                    }
                    return@transaction
                }
            }
        }
        catch (e:Exception){
            response = AuthorizationResponse(error = e.localizedMessage,statusCode = HttpStatusCode.InternalServerError)
        }
        return response
    }

}