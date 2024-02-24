package overloading

fun main() {
    /**
     * overloading
     * if you want the same function or behavior in a class with different argument
     */
    val calc = Calculator()

    println(calc.add(2, 4))
    println(calc.add(2.5, 2.2))
    println(calc.add(6f, 7f))
    println(calc.add(1, 2, 3))

    println(calc.min(9, 2))
    println(calc.min(17.2, 18.3))
    /*
        output:
            6
            4.7
            13.0
            6
            2
            17.2
     */

}