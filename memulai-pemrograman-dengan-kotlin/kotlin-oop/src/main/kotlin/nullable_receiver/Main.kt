package nullable_receiver

fun main() {
    /**
     * nullable receiver
     * this used if you have an object with null value in extensions
     * you can use safe call (?.), elvis operator (?:), or if else
     */
    val value: Int? = null
    val value1: Int? = null

    println(value.slice)
    println(value1.slice)
}

val Int?.slice: Int
    get() = this?.div(2) ?: 0