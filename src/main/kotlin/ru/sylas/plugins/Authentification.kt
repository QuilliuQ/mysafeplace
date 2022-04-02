package ru.sylas.plugins

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import org.jetbrains.exposed.sql.and
import ru.sylas.common.JWTConfig
import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.model.tables.auth.TokenT
import ru.sylas.model.tables.auth.UserTableT
import ru.sylas.model.tablesDAO.auth.TokenDao
import ru.sylas.model.tablesDAO.auth.UserTableDao

fun Application.configureAuthentification(){

    install(Authentication){

    jwt("auth-jwt") {
        verifier(JWTConfig.verifier)
        realm = "sylas.wsr"
        validate {
                validateCredential(it)
        }
    }
}
}

private fun validateCredential(jwtCredential: JWTCredential) =
    if (findUser(jwtCredential)) {
            JWTPrincipal(jwtCredential.payload)
        } else {
        null
    }


private fun findUser(jwtCredential: JWTCredential):Boolean{
    loggedTransaction {
         val email = jwtCredential.payload.getClaim("email").asString().guard {
            return@loggedTransaction
        }
        val password = jwtCredential.payload.getClaim("secret").asString().guard {
            return@loggedTransaction
        }
        val userDB = UserTableDao.find { UserTableT.email eq email and (UserTableT.secret eq password) }.firstOrNull().guard {
            return@loggedTransaction
        }
        TokenDao.find { TokenT.userId eq userDB.id }.guard {
            return@loggedTransaction
        }
    }
    return true
}