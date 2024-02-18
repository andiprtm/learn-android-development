package string_template

fun main() {
    /**
     * string template
     * this method is used to simplify your code if you want to concat the string with variable or expression
     */
    val name = "Andi"
    val hour = 7

    print("Hi $name, Office ${if (hour > 7) "already close" else "is open"}")
    /*
        output : Hi Andi, Office is open
     */

}