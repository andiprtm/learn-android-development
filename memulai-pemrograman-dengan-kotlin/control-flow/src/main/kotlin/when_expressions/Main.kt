package when_expressions

import kotlin.random.Random

fun main() {
    /**
     * when expressions
     * these expressions is like switch but in the easier way
     * else in this expression, it is mandatory
     */
    val value = 7

    when(value){
        6 -> println("value is 6")
        7 -> println("value is 7")
        8 -> println("value is 8")
        else -> println("value cannot be reached")
    }
    /*
        output : value is 7
     */

    // when can return value and can hold in a variable
    val stringOfValue = when (value) {
        6 -> "value is 6"
        7 -> "value is 7"
        8 -> "value is 8"
        else -> "value cannot be reached"
    }

    println(stringOfValue)

    // when can check the instance of a variable
    val anyType : Any = 100L
    when(anyType){
        is Long -> println("the value has a Long type")
        is String -> println("the value has a String type")
        else -> println("undefined")
    }
    /*
        output: the value has a Long type
     */

    // when also can check a numbers are in range or not
    val ranges = 10..50

    when(value){
        in ranges -> println("value is in the range")
        !in ranges -> println("value is outside the range")
    }
    /*
        output: value is outside the range
     */

    // when can capture the subject of when expression in a variable
    val registerNumber = when(val regis = getRegisterNumber()){
        in 1..50 -> 50 * regis
        in 51..100 -> 100 * regis
        else -> regis
    }

    println(registerNumber)
    /*
        output: 250
     */
}

fun getRegisterNumber() = Random.nextInt(100)