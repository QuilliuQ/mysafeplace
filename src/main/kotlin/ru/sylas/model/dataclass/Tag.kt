package ru.sylas.model.dataclass

import com.papsign.ktor.openapigen.APITag

enum class Tag(override val description: String) : APITag {

    Games("Version 1 API."),
    Auth("Unclassified API."),
}