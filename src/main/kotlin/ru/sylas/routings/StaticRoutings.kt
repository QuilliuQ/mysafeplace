package ru.sylas.routings

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Application.configureStaticRoutings() {
    routing {
        static("/api/images") {
            resources("files")
        }
    }
}