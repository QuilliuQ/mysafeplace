package ru.sylas.model.tables.auth

import org.jetbrains.exposed.dao.id.IntIdTable
import ru.sylas.model.tables.app.KeyDeviceT

object UserKeyDeviceT:IntIdTable("userKeyDevice") {
    val keyDeviceId = reference("keyDeviceId",KeyDeviceT)
    val userId = reference("userId",UserTableT)
}