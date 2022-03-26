package ru.sylas.plugins

import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.ktor.application.*
import kotlin.reflect.KType

fun Application.configureApiGenerator(){

    install(OpenAPIGen) {
        // basic info
        info {
            version = "0.0.1"
            title = "Children Games API"
            description = "Children Games API"
            contact {
                name = "Sylas"
                email = "main@sylas.ru"
            }
        }
        // describe the server, add as many as you want
        server("http://0.0.0.0:8080") {
            description = "Children Games API"
        }
        //optional custom schema object namer
        replaceModule(DefaultSchemaNamer, object: SchemaNamer {
            val regex = Regex("[A-Za-z0-9_.]+")
            override fun get(type: KType): String {
                return type.toString().replace(regex) { it.value.split(".").last() }.replace(Regex(">|<|, "), "_")
            }
        })
    }
}
