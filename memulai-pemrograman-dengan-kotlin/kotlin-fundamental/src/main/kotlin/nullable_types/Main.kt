package nullable_types

fun main() {
    /**
     * nullable type
     * this type is security feature from kotlin
     * you cant assign a null value, you must give a default value instead
     */

    //val text: String = null // compile time error
    /**
     * if you uncomment above statement, you will get an error
     */

    val text: String? = null // ready to go
    /**
     * here we go, this variable now can contain null but the variable cannot be process because is null
     */

    //val textLength = text.length // compile time error
    /**
     * if you uncomment above statement, you will get error because the text variable is null and it can be process
     */

    var string: String? = "Dicoding"
    if(string != null) { // smart cast
        print(string.length) // It works now!
    }
    /**
     * to process the variable you can use if expression to check it, is null or not
     * in code above, kotlin use smart cast to casting String? to String
     * but in kotlin also have an alternative solution, that is use is or !is
     */

    var obj: Any = "Dicoding"

    if(obj is String) {
        // Tidak membutuhkan casting secara eksplisit.
        println("String length is ${obj.length}")
    }
}