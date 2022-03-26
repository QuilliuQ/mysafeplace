package ru.sylas.plugins

import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger
import ru.sylas.di.appModule
import ru.sylas.di.authorizationAppModule
import ru.sylas.di.gameModule
import ru.sylas.di.statModule

fun Application.configureDI(){
    install(Koin) {
        SLF4JLogger()
        modules(authorizationAppModule, appModule, gameModule, statModule)
    }
}