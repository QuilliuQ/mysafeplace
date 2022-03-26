package ru.sylas.common

import com.papsign.ktor.openapigen.APITag

enum class Tag(override val description: String) : APITag {

    App("Рестрация приложения и устройства"),
    Auth("Работа с пользователем"),
    Games("Работа с играми"),
    Statistic(""),
    Watch(""),
    TV(""),
    Tablet("")
}