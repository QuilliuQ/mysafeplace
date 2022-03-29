package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.repository.apprepository.AppRepository
import ru.sylas.repository.apprepository.AppRepositoryImpl
import ru.sylas.service.appservice.AppService
import ru.sylas.service.appservice.AppServiceImpl

val appModule = module {
    single<AppService> {
        AppServiceImpl(get())
    }

    single<AppRepository> {
        AppRepositoryImpl()
    }

}