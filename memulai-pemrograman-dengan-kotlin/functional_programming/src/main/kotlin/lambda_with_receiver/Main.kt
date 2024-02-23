package lambda_with_receiver

fun main() {
    /**
     * lambda with receiver
     * this is used in DSl.
     * example for dsl is Gradle or database system with SQL
     */
    val message = buildString {
        append("Hello ")
        append("from ")
        append("lambda ")
    }

    println(message)
}

// without lambda receiver
fun buildString(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("Hello ")
    stringBuilder.append("from ")
    stringBuilder.append("lambda")
    return stringBuilder.toString()
}

fun buildString(action: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.action()
    return stringBuilder.toString()
}