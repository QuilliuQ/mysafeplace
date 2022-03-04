package ru.sylas.common

import io.ktor.application.*
import io.ktor.auth.*
import ru.sylas.model.dataclass.User

object Utils {
    inline fun <T> T?.guard(nullClause: () -> Nothing): T {
        return this ?: nullClause()
    }

}