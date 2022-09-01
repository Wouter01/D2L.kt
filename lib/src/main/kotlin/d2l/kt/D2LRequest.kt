package d2l.kt

import io.ktor.client.request.*
import io.ktor.http.*

object D2LRequest {
    class AuthError(message: String): Exception(message)

    var appID: String? = null
    var appKey: String? = null
    var userID: String? = null
    var userKey: String? = null
    var baseURL: String? = null

    fun build(method: HttpMethod = HttpMethod.Get, path: String): Parameters {
        val time = System.currentTimeMillis() / 1000
        val data = "${method.value}&${path.lowercase()}&${time}"

        return Parameters.build {
            append("x_a", appID ?: throw AuthError("App ID is not set."))
            append("x_b", userID ?: throw AuthError("User ID is not set."))
            append("x_c", D2LAuth.createHMAC(appKey ?: throw AuthError("App key is not set."), data))
            append("x_d", D2LAuth.createHMAC(userKey ?: throw AuthError("User key is not set."), data))
            append("x_t", "$time")
        }
    }
}