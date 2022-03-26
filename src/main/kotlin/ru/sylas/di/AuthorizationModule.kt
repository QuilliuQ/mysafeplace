package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.repository.AuthorizationRepository
import ru.sylas.repository.AuthorizationRepositoryImpl
import ru.sylas.service.authorizationservice.AuthorizationService
import ru.sylas.service.authorizationservice.AuthorizationServiceImpl


val authorizationAppModule = module(createdAtStart = true) {
    single<AuthorizationRepository>{
        AuthorizationRepositoryImpl()
    }
    single<AuthorizationService>() {
        AuthorizationServiceImpl(get())
    }


}