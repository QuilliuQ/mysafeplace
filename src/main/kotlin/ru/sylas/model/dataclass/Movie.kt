package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.annotations.Response

@Response
data class Movie(
    val source:String,
    val sector:List<Sector>
)




