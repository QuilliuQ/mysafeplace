package ru.sylas.routings

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import org.koin.ktor.ext.inject
import ru.sylas.model.dataclass.User
import ru.sylas.service.AuthorizationService

fun Application.configureRouting() {

    val service: AuthorizationService by inject()


    routing {


        authenticate("auth-jwt") {

        }

        post("/register"){
            val response = call.receive<User>()
            val result = service.registration(response)
            if(result.data != null){
                call.respond(result.statusCode,result.data!!)
            }
            else{
                call.respond(result.statusCode,result.error!!)
            }
        }
        post("/auth"){
            val response = call.receive<User>()
            val result = service.authorization(response)
            if(result.data != null){
                call.respond(result.statusCode,result.data!!)
            }
            else{
                call.respond(result.statusCode,result.error!!)
            }

        }
    }
}