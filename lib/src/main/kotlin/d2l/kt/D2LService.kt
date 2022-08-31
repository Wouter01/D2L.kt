package d2l.kt

enum class D2LService(val url: String) {
    LP("/d2l/api/lp/1.37/"),
    LE("/d2l/api/le/1.50/"),
    BAS("/d2l/api/bas/1.1/")
}