package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response


@Response
data class Statistic(
    val name: String,
    val statistic:Stats
)
