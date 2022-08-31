package d2l.kt

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

interface D2LRoute {

    val platform: D2LService

    val builder: HttpRequestBuilder

    suspend fun fetch() {
        with(builder) {
            port = 443
            host = "ufora.ugent.be"
            url.protocol = URLProtocol.HTTPS
            url.encodedPath = platform.url + url.encodedPath
            val secureParams = request.build(method, url.encodedPath)
            url.parameters.appendAll(secureParams)
        }
        val response: HttpResponse = HttpClient(CIO).request(builder)
    }

}