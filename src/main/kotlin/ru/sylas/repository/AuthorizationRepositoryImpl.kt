package ru.sylas.repository

import io.ktor.http.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.common.Hashing.sha256
import ru.sylas.common.JWTConfig
import ru.sylas.common.Utils.guard
import ru.sylas.model.*
import ru.sylas.model.dataclass.RefreshToken
import ru.sylas.model.dataclass.User
import ru.sylas.model.dataclass.UserToken
import ru.sylas.model.responses.AuthorizationResponse
import ru.sylas.model.responses.DefaultResponse
import ru.sylas.model.tables.Token
import ru.sylas.model.tables.Token.accessToken
import ru.sylas.model.tables.Token.expireAt
import ru.sylas.model.tables.Token.refreshToken
import ru.sylas.model.tables.UserTable
import ru.sylas.model.tablesDAO.TokenDao
import ru.sylas.model.tablesDAO.UserTableDao
import java.util.*

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
                    val tempRefreshToken = UUID.randomUUID().toString()
                    val userToken = JWTConfig.generateToken(user.password.sha256())
                    val expireTime = System.currentTimeMillis()+JWTConfig.getValidTime()
                    TokenDao.findById(userDB.id).also { tokenDB ->
                        tokenDB?.accessToken = userToken
                        tokenDB?.refreshToken = tempRefreshToken
                        tokenDB?.expireAt = expireTime

                    }.guard {
                        response = AuthorizationResponse(data = UserToken(userDB.id.toString(),
                            accessToken = userToken,
                            refreshToken =tempRefreshToken),
                            statusCode = HttpStatusCode.OK).also {
                            TokenDao.new(userDB.id.value)  {
                                accessToken = userToken
                                refreshToken = tempRefreshToken
                                expireAt = expireTime
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
                        val tempRefreshToken = UUID.randomUUID().toString()
                        val userToken = JWTConfig.generateToken(it.secret)
                        val expireTime = System.currentTimeMillis()+JWTConfig.getValidTime()
                        TokenDao.new(it.id.value) {
                            refreshToken = tempRefreshToken
                            accessToken = userToken
                            expireAt = expireTime
                        }.also { tokenDao ->
                            response = AuthorizationResponse(data =
                            UserToken(
                                userId = tokenDao.id.toString(),
                                accessToken = tokenDao.accessToken,
                                refreshToken = tokenDao.refreshToken),
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

    override fun refreshToken(refreshToken: RefreshToken): AuthorizationResponse {
        var response = AuthorizationResponse()
        try {
            transaction {
                TokenDao.find{ Token.refreshToken eq refreshToken.token }.singleOrNull().also { token->
                    UserTableDao.findById(token!!.id).let { user->
                        if (user != null) {
                            val tempToken = JWTConfig.generateToken(user.secret)
                            val tempRefreshToken = UUID.randomUUID().toString()
                            token.accessToken = tempToken
                            token.refreshToken = tempRefreshToken
                            token.expireAt = System.currentTimeMillis()+JWTConfig.getValidTime()
                        }
                        response = AuthorizationResponse(data = UserToken(
                            userId = token.id.toString(),
                            accessToken = token.accessToken,
                            refreshToken =token.refreshToken


                        )
                        )
                    }
                }
            }
        }
        catch (e:Exception){
            response = AuthorizationResponse(error = e.localizedMessage, statusCode = HttpStatusCode.InternalServerError)
        }
        return response

    }
}