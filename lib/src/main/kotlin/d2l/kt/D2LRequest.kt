package d2l.kt

import io.ktor.client.request.*
import io.ktor.http.*

class D2LRequest private constructor(val appID: String, val appKey: String, val userID: String, val userKey: String) {

    companion object {

        private var instance: D2LRequest? = null

        fun getInstance(val appID: String, val appKey: String, val userID: String, val userKey: String): D2LRequest {
            if (instance == null) {
                instance = D2LRequest(appID, appKey, userID, userKey)
            }
            return instance!!
        }

    }

    fun build(method: HttpMethod = HttpMethod.Get, path: String): Parameters {
        val time = System.currentTimeMillis() / 1000
        val data = "${method.value}&${path.lowercase()}&${time}"
        println(data)
        return Parameters.build {
            append("x_a", appID)
            append("x_b", userID)
            append("x_c", D2LAuth.createHMAC(appKey, data))
            append("x_d", D2LAuth.createHMAC(userKey, data))
            append("x_t", "$time")
        }
    }
}