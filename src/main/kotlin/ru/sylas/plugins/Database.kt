package ru.sylas.plugins

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.model.tables.app.App
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.app.Mobile
import ru.sylas.model.tables.auth.Token
import ru.sylas.model.tables.auth.UserKeyDevice
import ru.sylas.model.tables.auth.UserTable
import ru.sylas.model.tables.game.GameT
import ru.sylas.model.tables.game.GameTypeT
import ru.sylas.model.tables.game.SizeGameImage
import ru.sylas.model.tables.pincode.PinCodeT
import ru.sylas.model.tables.stats.StatsT
import ru.sylas.model.tables.stats.UserStat

fun configureDatabase() {
    Database.connect(
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Rfnzvbcfqkj1",
        url = "jdbc:postgresql://localhost:5432/wsr"
    )
    transaction {
        SchemaUtils.create (App, Mobile,KeyDeviceT,Token,UserKeyDevice,UserTable,GameT,GameTypeT,SizeGameImage,PinCodeT,StatsT,UserStat)
    }
}