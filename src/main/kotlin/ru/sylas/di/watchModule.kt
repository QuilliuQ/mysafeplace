package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.service.watchservice.WatchService
import ru.sylas.service.watchservice.WatchServiceImpl

val watchModule = module {

    single<WatchService> {
        WatchServiceImpl()
    }
}