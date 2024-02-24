package declaring_generic_function

fun main() {
    /**
     * generic function
     * Generic in a function is needed when we create a function related to List.
     * For example, a list that can be used for various types and is not fixed to a particular type.
     *
     * declaration of generic function:
     * fun <T> run(): T { // the angel bracket is placed before the name of function
     *     /*...*/
     * }
     */
    val numbers = (1..100).toList()
    print(numbers.slice<Int>(1..10))
}