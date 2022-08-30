import retrofit2.Retrofit

enum class D2LService(val url: String) {
    LP("/d2l/api/lp/1.37/"),
    LE("/d2l/api/le/1.50/"),
    BAS("/d2l/api/bas/1.1/")
}

interface D2LRoute {

    val platform: D2LService


}

class CourseRoute: D2LRoute {
    override val platform: D2LService = D2LService.LE

    fun test() {
       RetrofitHelper.getInstance()
    }
}

object RetrofitHelper {

    val baseUrl = "https://quotable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}