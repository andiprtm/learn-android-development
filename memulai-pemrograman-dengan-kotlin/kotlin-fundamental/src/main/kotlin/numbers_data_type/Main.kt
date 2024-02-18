package numbers_data_type

fun main() {
    /**
     * numeric data type
     * This data type is specifically used to hold numbers such as decimals, integers, and bytes
     *
     * Int
     * this data type contains numeric data from the range -2^31 to +2^31-1. 32 bit size
     *
     * Long
     * this type basically contains larger integer values that are from the range -2^63 to 2^63-1. 64 bit size
     *
     * Short
     * this type contains small integer values as it only has a size of 16 bits
     *
     * Byte
     * This type holds smaller values than the short type. usually used to hold stream or network files
     *
     * Double
     * this type is the long version of decimal. contains 15-16 numbers behind a comma
     *
     * Float
     * this type is the base of a decimal number. contains 6-7 digits behind a comma
     */
    val maxInt = Int.MAX_VALUE
    val overRangeInt = Int.MAX_VALUE + 1 // This operation has led to an overflow

    println("Max Int: $maxInt")
    println("Over range Int: $overRangeInt")

    /*
       Output :
       Max Int: 2147483647
       Over range Int: -2147483648
    */

    /**
     * convert type
     *
     * toByte(): Byte
     * toShort(): Short
     * toInt(): Int
     * toLong(): Long
     * toFloat(): Float
     * toDouble(): Double
     * toChar(): Char
     */
    val stringNumber = "23"
    val intNumber = 3

    println(intNumber + stringNumber.toInt())
    /*
       output: 26
    */

    /**
     * readable number
     * jus use underscore like this 100_000
     */
    val readableNumber = 1_000_000
    print(readableNumber)

    /*
        output : 1000000
    */
}