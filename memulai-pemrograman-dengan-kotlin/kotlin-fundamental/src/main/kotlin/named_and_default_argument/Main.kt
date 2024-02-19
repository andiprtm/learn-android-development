package named_and_default_argument

fun main() {
    /**
     * named argument
     * this method use make things easier when call a function with argument
     * you have not to remember the argument order in a function
     * also you can set the default value if accidentally you forget to fill the argument
     */
    val fullName = getFullName(middle = " is " , first = "Kotlin", last = "Awesome")
    println(fullName)
    /*
        output : Kotlin  is  Awesome
     */

    println(getFullName2())
    /*
        output : Kotlin  is  Awesome
     */
}

fun getFullName(first: String, middle: String, last: String): String {
    return "$first $middle $last"
}

fun getFullName2(
    first: String = "Kotlin",
    middle: String = " is ",
    last: String = "Awesome"): String {
    return "$first $middle $last"
}