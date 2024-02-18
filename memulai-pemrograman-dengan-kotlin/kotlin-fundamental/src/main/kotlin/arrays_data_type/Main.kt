package arrays_data_type

fun main() {
    /**
     * array data type
     * this data type contain several object in a variable
     * because array represented by class, so this data type have a function that is get, set, and size
     * in array you can insert different of type like number, string, float, etc
     *
     * this is more specific function in array
     *
     * intArrayOf() : IntArray
     * booleanArrayOf() : BooleanArray
     * charArrayOf() : CharArray
     * longArrayOf() : LongArray
     * shortArrayOf() : ShortArray
     * byteArrayOf() : ByteArray
     */
    val mixArray = arrayOf(1, 3, 5, 7 , "Dicoding" , true)
    for( i in mixArray){
        print(i)
    }
    /*
        output : 1357Dicodingtrue
     */
}