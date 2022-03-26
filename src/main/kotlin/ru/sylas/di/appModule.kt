package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.service.appservice.AppService
import ru.sylas.service.appservice.AppServiceImpl

val appModule = module {
    single<AppService> {
        AppServiceImpl()
    }
}