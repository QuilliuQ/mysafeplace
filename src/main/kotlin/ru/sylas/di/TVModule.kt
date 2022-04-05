package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.repository.tv.TVRepository
import ru.sylas.repository.tv.TVRepositoryImpl
import ru.sylas.service.tvservice.TVService
import ru.sylas.service.tvservice.TVServiceImpl

val TVModule = module {
    single<TVService>{
        TVServiceImpl(get())
    }
    single<TVRepository>{
        TVRepositoryImpl()
    }
}