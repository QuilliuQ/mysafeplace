package ru.sylas.model.requestdataclasses

import com.papsign.ktor.openapigen.annotations.Response
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam

@Response
data class PinCode(
    @QueryParam("pin")val pinCode: Int
)
