package ru.sylas.di

import org.koin.dsl.module
import ru.sylas.repository.stats.StatisticRepository
import ru.sylas.repository.stats.StatisticRepositoryImpl
import ru.sylas.service.statisticservice.StatisticService
import ru.sylas.service.statisticservice.StatisticServiceImpl

val statModule = module {
    single<StatisticService> {
        StatisticServiceImpl(get())
    }
    single<StatisticRepository>{
        StatisticRepositoryImpl()
    }
}