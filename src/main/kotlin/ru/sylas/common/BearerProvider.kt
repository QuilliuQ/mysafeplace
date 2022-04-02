package ru.sylas.common

import com.papsign.ktor.openapigen.model.Described
import com.papsign.ktor.openapigen.model.security.HttpSecurityScheme
import com.papsign.ktor.openapigen.model.security.SecuritySchemeModel
import com.papsign.ktor.openapigen.model.security.SecuritySchemeType
import com.papsign.ktor.openapigen.modules.providers.AuthProvider
import com.papsign.ktor.openapigen.route.path.auth.OpenAPIAuthenticatedRoute
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.throws
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.util.pipeline.*
import ru.sylas.exceptions.UnauthorizedException
import ru.sylas.model.dataclass.ErrorEx
import ru.sylas.model.requestdataclasses.AuthUser


enum class Scopes : Described

object BearerProvider : AuthProvider<AuthUser> {

    // description for OpenAPI model
    override val security =
        listOf(
            listOf(
                AuthProvider.Security(
                    SecuritySchemeModel(
                        referenceName = "Bearer Token",
                        name = "bearerAuth",
                        type = SecuritySchemeType.http,
                        scheme = HttpSecurityScheme.bearer,
                    ), emptyList<Scopes>()
                )
            )
        )

    override suspend fun getAuth(pipeline: PipelineContext<Unit, ApplicationCall>): AuthUser {

        return pipeline.context.authentication.principal()
            ?: throw UnauthorizedException("Неавторизованый доступ")
    }


    override fun apply(route: NormalOpenAPIRoute): OpenAPIAuthenticatedRoute<AuthUser> {
        return OpenAPIAuthenticatedRoute(route.ktorRoute.authenticate("auth-jwt") {

        }, route.provider.child(), this)
            .throws(
                status = HttpStatusCode.Unauthorized.description("Неавторизованый доступ"),
                example = ErrorEx("Неавторизованный доступ"),
                UnauthorizedException::class
            )
    }
}