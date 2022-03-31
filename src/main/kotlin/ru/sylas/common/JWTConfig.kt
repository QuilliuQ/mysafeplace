package ru.sylas.common

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JWTConfig {
    private const val secret = "gdfadRERWoi2q24AD423"
    private const val issuer = "sylas.wsr"
    private val algorithm = Algorithm.HMAC512(secret)


    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()


    fun generateToken(secret: String): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("secret", secret)
        .sign(algorithm)



}