package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.repository.watch.WatchRepository
import ru.sylas.repository.watch.WatchRepositoryImpl
import ru.sylas.service.watchservice.WatchService
import ru.sylas.service.watchservice.WatchServiceImpl

val watchModule = module {

    single<WatchService> {
        WatchServiceImpl(get())
    }
    single<WatchRepository>{
        WatchRepositoryImpl()
    }
}