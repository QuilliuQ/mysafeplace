package ru.sylas.plugins

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.sylas.common.generateDBConstants
import ru.sylas.model.tables.app.AppT
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.app.MobileT
import ru.sylas.model.tables.auth.TokenT
import ru.sylas.model.tables.auth.UserKeyDeviceT
import ru.sylas.model.tables.auth.UserTableT
import ru.sylas.model.tables.game.*
import ru.sylas.model.tables.pincode.PinCodeT
import ru.sylas.model.tables.stats.StatsT
import ru.sylas.model.tables.stats.UserStatT

fun configureDatabase() {
    Database.connect(
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Rfnzvbcfqkj1",
        url = "jdbc:postgresql://localhost:5432/wsr"
    )
    transaction {
        SchemaUtils.create(
            AppT,
            MobileT,
            KeyDeviceT,
            TokenT,
            UserKeyDeviceT,
            UserTableT,
            AnimalT,
            CorrespondingT,
            GameT,
            GameSourcesT,
            GameTypeT,
            GameSizeT,
            OneGameT,
            SourceT,
            SourceTypeT,
            PinCodeT,
            StatsT,
            UserStatT,

        )
        generateDBConstants()
    }
}