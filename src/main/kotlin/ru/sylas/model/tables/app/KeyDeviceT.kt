package ru.sylas.model.tables.app

import org.jetbrains.exposed.dao.id.IntIdTable

object KeyDeviceT: IntIdTable("keyDevice"){
    val mobileId  = reference("mobileId",Mobile)
    val keyDevice  = text("keyDevice")
}