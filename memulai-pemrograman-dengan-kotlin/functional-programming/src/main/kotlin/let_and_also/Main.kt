package let_and_also

fun main() {
    /**
     * Scope Function with Lambda Argument
     */
    //let
    /**
     * the return value depends on the expression inside the lambda block
     */
    val message: String? = null
    message?.let {
        val length = it.length * 2
        val text = "text length $length"
        println(text) // the return type is unit because just print it
    } ?: run {
        val text = "message is null"
        println(text)
    }

    // also
    /**
     * this function is used when you just want to use the context of the object without having to change the object
     */
    val text = "Hello Kotlin"
    val result = text.also {
        println("value length -> ${it.length}")
    }

    println("text -> $result")
}