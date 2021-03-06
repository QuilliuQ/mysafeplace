package ru.sylas.plugins

import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger
import ru.sylas.di.authorizationAppModule

fun Application.configureDI(){
    install(Koin) {
        SLF4JLogger()
        modules(authorizationAppModule)
    }
}