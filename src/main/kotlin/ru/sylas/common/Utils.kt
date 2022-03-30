package ru.sylas.common

import com.papsign.ktor.openapigen.route.path.auth.OpenAPIAuthenticatedRoute
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import io.ktor.auth.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object Utils {
    inline fun <T> T?.guard(nullClause: () -> Nothing): T {
        return this ?: nullClause()
    }

    inline fun NormalOpenAPIRoute.auth(route: OpenAPIAuthenticatedRoute<UserIdPrincipal>.() -> Unit): OpenAPIAuthenticatedRoute<UserIdPrincipal> {
        return BearerProvider.apply(this).apply(route)
    }

    fun <T> loggedTransaction(statement: Transaction.() -> T): T  = transaction {
        addLogger(StdOutSqlLogger)
        statement()
    }

}