package object_everywhere

fun main() {
    /**
     * object everywhere
     * kotlin is famous with the term "object everywhere"
     * because primitive value like string, integer, char, boolean is an object
     */
    val someString = "123"
    val someInt = someString.toInt()
    val someOtherString = "12.34"
    val someDouble = someOtherString.toDouble()

    println(someInt is Int)
    println(someDouble is Double)
    /*
        output:
            true
            true
     */
}