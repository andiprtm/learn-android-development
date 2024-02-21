package extensions

fun main() {
    /**
     * extensions
     * Kotlin allows us to add a new function to a class without having to inherit that class
     */
    10.printInt()
    /*
       output : value 10
    */

    println(10.plusThree())
    /*
       output : 13
    */

    println(10.slice)
    /*
       output : 5
    */

    val result = 5 sum 3 // use infix function
    /*
       output : 8
    */
}

val Int.slice: Int
    get() = this / 2

fun Int.printInt() {
    println("value $this")

}

fun Int.plusThree(): Int {
    return this + 3
}

infix fun Int.sum(value: Int): Int {
    return this + value
}