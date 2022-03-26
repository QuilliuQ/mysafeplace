package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.parameters.QueryParam

data class PinCode(
    @QueryParam("pin")val pinCode: Int
)
