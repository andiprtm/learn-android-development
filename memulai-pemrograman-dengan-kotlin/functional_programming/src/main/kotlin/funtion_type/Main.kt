package funtion_type

typealias Arithmetic = (Int, Int) -> Int // use typealias to define the function type.
typealias ArithmeticNullable = ((Int, Int) -> Int)? // add ? at the end of declaration. to mark as nullable function type
fun main() {
    /**
     * function type
     * (parameter1, parameter2) -> return type
     * we can simplify the several functions that have the same function type
     */
    val sum: Arithmetic = { valueA, valueB -> valueA + valueB }

    val multiply: Arithmetic = { valueA, valueB -> valueA * valueB }

    // to use the above function, use invoke() operator
    val sumResult = sum.invoke(10, 10)
    val multiplyResult = multiply.invoke(20, 20)

    // or not use invoke operator()
    // val sumResult = sum(10, 10)
    // val multiplyResult = multiply(20, 20)

    println(sumResult)
    println(multiplyResult)

    // you can also mark the function type as a nullable function
    val sumNullable: ArithmeticNullable = { valueA, valueB -> valueA + valueB }

    val sumResultNullable = sumNullable?.invoke(10, 10) // use safe call if your function type is support nullable

    println(sumResultNullable)
}