package ru.sylas.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    Database.connect(
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Repmvbyffyz",
        url = "jdbc:postgresql://192.168.49.146:5432/main"
    )
}