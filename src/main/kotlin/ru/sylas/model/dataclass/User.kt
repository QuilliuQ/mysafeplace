package ru.sylas.model.dataclass

import io.ktor.auth.*
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

data class User(
    val id : UUID? = null,
    val login: String,
    val password: String,
    val firstName: String? = null,
    val avatar: String? = null
):Principal









