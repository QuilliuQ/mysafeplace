package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.parameters.QueryParam

data class Competitor(
    @QueryParam("Номер участника в формате \"Competitor-1\"") val competitor: String
)
