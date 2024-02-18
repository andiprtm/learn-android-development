package char_data_type

fun main() {
    /**
     * char data type
     * char is data type that contain a single character
     * you can define the Type of variable to Char
     * example :
     *     val character: Char = 'A'
     */
    val character = 'A'

    /**
     * fun fact:
     * You can increment or decrement characters, as characters are representations of unicode
     */
    var vocal = 'A'

    println("Vocal " + vocal++)
    println("Vocal " + vocal++)
    println("Vocal " + vocal++)
    println("Vocal " + vocal--)
    println("Vocal " + vocal--)
    println("Vocal " + vocal--)
    println("Vocal " + vocal--)

    /*
       output:
           Vocal A
           Vocal B
           Vocal C
           Vocal D
           Vocal C
           Vocal B
           Vocal A
    */
}