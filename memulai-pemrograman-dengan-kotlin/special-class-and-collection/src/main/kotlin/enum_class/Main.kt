package enum_class

fun main() {
    /**
     * enum class
     * this is used to store a constant variable
     */
    val colors: Array<Color> = enumValues()
    colors.forEach {color ->
        println(color)
    }
    /*
        output:
            RED
            GREEN
            BLUE
     */

    val color: Color = enumValueOf("RED")
    println("Color is $color")
    /*
        output: Color is RED
     */

    print("Position RED is ${color.ordinal}")
    /*
        output: Position RED is 0
     */

    when(color){
        Color.RED -> print("Color is Red")
        Color.BLUE -> print("Color is Blue")
        Color.GREEN -> print("Color is Green")
    }
    /*
        output: Position RED is 0Color is Red
     */
}