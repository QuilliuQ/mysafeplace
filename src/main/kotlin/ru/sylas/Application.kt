package ru.sylas

import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import ru.sylas.plugins.*
import ru.sylas.routings.ApiGeneratorRouter
import ru.sylas.routings.configureRouting
import ru.sylas.routings.configureStaticRoutings
import ru.sylas.routings.gameRoutings

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0") {

        configureMonitoring()
        configureStatusPages()
        configureAuthentification()
        configureStaticRoutings()
        configureDatabase()
        configureSerialization()
        configureDI()

        configureApiGenerator()
        ApiGeneratorRouter()
        gameRoutings()
        configureRouting()
    }.start(wait = true)
}
