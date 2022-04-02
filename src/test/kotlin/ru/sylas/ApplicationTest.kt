package ru.sylas

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import ru.sylas.routings.authorizationRouting

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ authorizationRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}