package function_inside_function

fun main() {
    /**
     * function inside function
     * like any other function, it is used to reduce repetitive logic in the function.
     */
}

// before use inside function
fun sumBefore(valueA: Int, valueB: Int, valueC: Int): Int {
    if (valueA == 0) {
        throw IllegalArgumentException("valueA must be better than 0")
    }

    if (valueB == 0) {
        throw IllegalArgumentException("valueB must be better than 0")
    }

    if (valueC == 0) {
        throw IllegalArgumentException("valueC must be better than 0")
    }

    return valueA + valueB + valueC
}

// after use inside function
fun sumAfter(valueA: Int, valueB: Int, valueC: Int): Int {
    fun validateNumber(value: Int) {
        if (value == 0) {
            throw IllegalArgumentException("value must be better than 0")
        }
    }

    validateNumber(valueA)
    validateNumber(valueB)
    validateNumber(valueC)

    return valueA + valueB + valueC
}

// we can also use a function extension inside function
fun sumExtension(valueA: Int, valueB: Int, valueC: Int): Int {
    fun Int.validateNumber(){
        if (this == 0) {
            throw IllegalArgumentException("value must be better than 0")
        }
    }

    valueA.validateNumber()
    valueB.validateNumber()
    valueC.validateNumber()

    return valueA + valueB + valueC
}