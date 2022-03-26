package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.service.gameservice.GameServiceImpl
import ru.sylas.service.gameservice.GameService

val gameModule = module {
    single<GameService> {
        GameServiceImpl()
    }
}