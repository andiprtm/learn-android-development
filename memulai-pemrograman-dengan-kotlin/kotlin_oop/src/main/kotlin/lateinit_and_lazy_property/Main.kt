package lateinit_and_lazy_property

lateinit var name: String

val fullName: String by lazy {
    "Dicoding Miaw"
}
fun main() {
    /**
     * this feature helps you to initialize the variable later
     * the different is "lateinit" always use var,
     * and the "lazy" always use val
     * the lazy property is used to initialize the variable when it really needed
     */
    lateinit var age: String
    name = "Dicoding Miaw"
    if (::name.isInitialized) // access to the same file
        println(name.length)
    else
        println("Not Initialized")
    /*
        output: 13
     */

    println(Animal::name) // to access with different file
    /*
        output: property name (Kotlin reflection is not available)
     */

    println(fullName.length)
}