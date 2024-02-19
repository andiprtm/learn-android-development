package range

fun main() {
    /**
     * range.
     * range is one of the types in kotlin.
     * range provide a range of numbers
     */
    var rangeInt = 1..10
    rangeInt.forEach { print("$it ") }
    /*
        output: 1 2 3 4 5 6 7 8 9 10
     */

    println()

    // step
    var rangeInt2 = 1..10 step 2
    rangeInt.forEach {
        print("$it ")
    }
    println()
    println(rangeInt.step)
    /*
        output:
            1 2 3 4 5 6 7 8 9 10
            1
     */

    // down to
    val tenToOne = 10.downTo(1)
    if (11 !in tenToOne) {
        println("No value 11 in Range ")
    }
    /*
        output: No value 11 in Range
    */
}