package d2l.kt

import d2l.kt.routes.CourseRoute
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun main() {
    val request = D2LRequest.getInstance(
        "",
        "",
        "",
        "")
    CourseRoute.Courses().fetch()
}

