package recursion

fun main() {
    /**
     * recursion
     * the program will create a stack of frames with a number based on the value of n where each frame will consume memory.
     * will be a problem if there are too many recursions
     */
    println("Factorial 9999 is: ${factorial(9999)}") // Exception in thread "main" java.lang.StackOverflowError
}
fun factorial(n: Int): Int {
    return if (n == 1) {
        n
    } else {
        n * factorial(n - 1)
    }
}