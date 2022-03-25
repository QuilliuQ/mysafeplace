package ru.sylas.common

import com.papsign.ktor.openapigen.APIException
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
import ru.sylas.model.dataclass.ResponseError

class UnauthorizedException(message: String) : RuntimeException(message)
class ForbiddenException(message: String) : RuntimeException(message)

// even if we don't need scopes at all, an empty enum has to be there, see https://github.com/papsign/Ktor-OpenAPI-Generator/issues/65
enum class Scopes : Described

object BasicAuthProvider : AuthProvider<UserIdPrincipal> {

    // description for OpenAPI model
    override val security =
        listOf(
            listOf(
                AuthProvider.Security(
                    SecuritySchemeModel(
                        referenceName = "refName",
                        name = "basicAuth",
                        type = SecuritySchemeType.http,
                        scheme = HttpSecurityScheme.bearer,
                    ), emptyList<Scopes>()
                )
            )
        )

    // gets auth information at runtime
    override suspend fun getAuth(pipeline: PipelineContext<Unit, ApplicationCall>): UserIdPrincipal {
        return pipeline.context.authentication.principal()
            ?: throw UnauthorizedException("Unable to verify given credentials, or credentials are missing.")
    }

    // convert normal route to authenticated route including OpenAPI meta information
    // TODO OpenAPI: Not listed as available auths at path level
    override fun apply(route: NormalOpenAPIRoute): OpenAPIAuthenticatedRoute<UserIdPrincipal> {
        return OpenAPIAuthenticatedRoute(route.ktorRoute.authenticate("auth-jwt") { }, route.provider.child(), this)
            .throws(
                status = HttpStatusCode.Unauthorized.description("Your identity could not be verified."),
                example = ResponseError(HttpStatusCode.Unauthorized, "Missing authorization to access this route."),
                gen = { e: UnauthorizedException -> return@throws ResponseError(HttpStatusCode.Unauthorized, e.message) }
            )
            .throws(
                status = HttpStatusCode.Forbidden.description("Your access rights are insufficient."),
                example = ResponseError(HttpStatusCode.Forbidden, "Insufficient access permissions for this route."),
                gen = { e: ForbiddenException -> return@throws ResponseError(HttpStatusCode.Forbidden, e.message) }
            )
    }
}