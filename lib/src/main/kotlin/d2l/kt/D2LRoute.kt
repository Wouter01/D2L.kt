package d2l.kt

import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import d2l.kt.models.Course
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend inline fun <reified T> fetch(platform: D2LService = D2LService.LP, builder: HttpRequestBuilder.() -> Unit): T {
    val request = HttpRequestBuilder()
    builder.invoke(request)

    with(request) {
        port = 443
        host = "ufora.ugent.be"
        url.protocol = URLProtocol.HTTPS
        url.encodedPath = platform.url + url.encodedPath
        val secureParams = D2LRequest.build(method, url.encodedPath)
        url.parameters.appendAll(secureParams)
    }

    val response: HttpResponse = HttpClient(CIO).request(request)

    val mapper = jacksonObjectMapper()
    mapper.propertyNamingStrategy = PropertyNamingStrategies.UPPER_CAMEL_CASE

    return mapper.readValue(response.bodyAsText())
}