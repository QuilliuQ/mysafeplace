package ru.sylas.plugins.LogT

import org.apache.juli.logging.Log
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object LogT:IntIdTable("logs") {
    val logTask = text("logTask")
    val logTrans = text("logTrans")
    val body = text("body")
}

class LogDao(id:EntityID<Int>):IntEntity(id){
    companion object:IntEntityClass<LogDao>(LogT)
    var logTask by LogT.logTask
    var logTrans by LogT.logTask
    var body by LogT.body
}