package ru.sylas.common

import com.fasterxml.jackson.core.JacksonException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.throws
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import ru.sylas.exceptions.HellException
import ru.sylas.model.dataclass.ErrorEx

fun Application.myApiRouting(config: NormalOpenAPIRoute.() -> Unit) {
    routing {
        NormalOpenAPIRoute(
            this,
            application.feature(OpenAPIGen).globalModuleProvider
        ).throws(
            status = HttpStatusCode.BadRequest.description("Проверьте корректность запроса"),
            gen = {_:MissingKotlinParameterException -> return@throws  "Проверьте корректность запроса" }
        ).throws(
            status = HttpStatusCode.BadRequest.description("Проверьте корректность запроса"),
            example = ErrorEx("Проверьте корректность запроса"),
            JacksonException::class
        ).throws(
            status = HttpStatusCode.InternalServerError.description("Внутренная ошибка сервера"),
            example = ErrorEx("Внутренная ошибка сервера"),
            HellException::class
        )
            .apply(config)
    }
}
