package high_order_function

fun main() {
    /**
     * high-order function.
     * a function that uses another function as a parameter makes a return type, or both.
     */
    // before
    printResult(10 ,sum)

    // after
    /**
     * Furthermore, there is an interesting writing convention in Kotlin,
     * which is that if the last parameter to a function is a lambda,
     * the argument can be written outside parenthesis like this.
     */
    printResult(10){ value ->
        value + value
    }

    // inline function
    /**
     * high-order function can affect in performance
     * if you call the same high-order function that's mean the compiler need to create another instance
     */
    printResult(10){ value ->
        value + value
    }
    printResult(20){ value ->
        value + value
    }
}

/**
 * add keyword inline to resolve the problem about high-order function
 */
inline fun printResult(value: Int, sum: (Int) -> Int) {
    val result = sum(value)
    println(result)
}

var sum: (Int) -> Int = { value -> value + value }