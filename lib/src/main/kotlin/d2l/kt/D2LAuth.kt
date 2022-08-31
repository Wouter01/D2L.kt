package d2l.kt

import java.net.URL
import java.net.URLEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

class D2LAuth {
    companion object {
        fun buildLoginURL(appID: String, appKey: String, baseURL: URL, callbackURL: URL): URL {
            val hmacKey = createHMAC(appKey, callbackURL.toString())
            val encodedURL = URLEncoder.encode(callbackURL.toString())
            return URL("${baseURL}/d2l/auth/api/token?x_b=${hmacKey}&x_target=${encodedURL}&type=mobile&x_a=${appID}")
        }

        private fun convertBase64String(string: String): String {
            return string
                .replace('+', '-')
                .replace('/', '_')
                .replace("=", "")
        }

        fun createHMAC(key: String, data: String): String {
            val mac = Mac.getInstance("HmacSHA256")
            val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
            mac.init(secretKey)
            val final = mac.doFinal(data.toByteArray())
            val base64Encoded = Base64.getEncoder().encodeToString(final)
            return convertBase64String(base64Encoded)
        }
    }
}