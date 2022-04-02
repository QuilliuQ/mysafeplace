package ru.sylas.common

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import ru.sylas.model.tablesDAO.auth.UserTableDao

object JWTConfig {
    private const val secret = "gdfadRERWoi2q24AD423"
    private const val issuer = "sylas.wsr"
    private val algorithm = Algorithm.HMAC512(secret)


    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()


    fun generateToken(user: UserTableDao): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("email",user.email)
        .withClaim("secret", user.secret)
        .sign(algorithm)



}