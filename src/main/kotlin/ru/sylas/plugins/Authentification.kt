package ru.sylas.plugins

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import ru.sylas.common.JWTConfig
import ru.sylas.model.dataclass.User
import ru.sylas.model.tables.UserTable.login

fun Application.configureAuthentification(){

    install(Authentication){

    jwt("auth-jwt") {
        verifier(JWTConfig.verifier)
        realm = "sylas.bonus"
        validate {
            try {
                val login = it.payload.getClaim("login").asString()
                val password = it.payload.getClaim("password").asString()
                if(!login.isNullOrEmpty() && !password.isNullOrEmpty()){
                    User(login = login, password = password)
                }else{
                    null
                }
            }
            catch (cause:Throwable){
                println(cause.message)
                null
            }

        }
    }
}
}