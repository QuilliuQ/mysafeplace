package ru.sylas

import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import ru.sylas.plugins.*
import ru.sylas.routings.*

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {

        configureMonitoring()
        configureStatusPages()
        configureAuthentification()
        configureStaticRouting()
        configureDatabase()
        configureSerialization()
        configureDI()
        configureApiGenerator()
        apiGeneratorRouter()
        appRouting()
        authorizationRouting()
        gameRouting()
        statsRouting()
//        configureWatch()
//        configureTVRouting()
    }.start(wait = true)
}
