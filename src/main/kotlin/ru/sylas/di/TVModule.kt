package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.service.tvservice.TVService
import ru.sylas.service.tvservice.TVServiceImpl

val TVModule = module {
    single<TVService>{
        TVServiceImpl()
    }
}