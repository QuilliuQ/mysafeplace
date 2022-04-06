package ru.sylas.plugins.LogT

import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.io.File

fun Application.logs(){
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }
    routing {
        get("/logs") {
            val fileName = "resources/myApp.log"
            val logs = File(fileName).readLines().reversed()
            call.respond(FreeMarkerContent("index.ftl", mapOf("entries" to logs), ""))

        }
    }
}