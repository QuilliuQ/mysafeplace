package ru.sylas.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.model.tables.app.App
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.app.Mobile

fun Application.configureDatabase() {
    Database.connect(
        driver = "org.postgresql.Driver",
        user = "developuser",
        password = "develop",
        url = "jdbc:postgresql://192.168.51.3:5432/wsr"
    )
    transaction {
        SchemaUtils.create (App, Mobile,KeyDeviceT)
    }
}