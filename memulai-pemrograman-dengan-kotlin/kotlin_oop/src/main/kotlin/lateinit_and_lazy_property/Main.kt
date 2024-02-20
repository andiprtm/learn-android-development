package lateinit_and_lazy_property

lateinit var name: String
fun main() {
    /**
     * this feature helps you to initialize the variable later
     * the different is "lateinit" always use var,
     * and the "lazy" always use val
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
}