package ru.sylas.common

import com.papsign.ktor.openapigen.route.path.auth.OpenAPIAuthenticatedRoute
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import io.ktor.auth.*

object Utils {
    inline fun <T> T?.guard(nullClause: () -> Nothing): T {
        return this ?: nullClause()
    }

    inline fun NormalOpenAPIRoute.auth(route: OpenAPIAuthenticatedRoute<UserIdPrincipal>.() -> Unit): OpenAPIAuthenticatedRoute<UserIdPrincipal> {
        return BearerProvider.apply(this).apply(route)
    }


}