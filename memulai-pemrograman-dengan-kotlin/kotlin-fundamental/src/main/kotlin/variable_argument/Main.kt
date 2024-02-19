package variable_argument

fun main() {
    /**
     * variable argument used to simplify argument with the same type
     * example:
     *     if you're building an addition calculator, and you want to give infinite input to the calculator function
     *     not possible if you use traditional arguments
     *     use this variable argument instead
     *
     * because variable argument bases on an array,
     * so we can use function that can use in an array like sum or size
     *
     * variable argument rules
     *
     * in a function cannot be more than one vararg
     * if you use more than one argument, the vararg should be placed in the last of argument
     * if you want the vararg in the first position in the argument. you should use named argument for argument with any type except vararg went you called that function
     */
    val number = sumNumbers(10, 20, 30, 40)
    println(number)
    /*
        output : 100
     */

    /**
     * you can input an array to the variable argument with spread operator
     */
    val numberArray = intArrayOf(10, 20, 30, 40)
    println(sumNumbers(10, 20, 20, *numberArray , 10))}
    /*
    output : 160
    */

fun sumNumbers(vararg number: Int): Int {
    return number.sum()
}