package companion_object

const val LIBRARY_NAME = "Dicoding Library" // OK
// const val LIBRARY_NAME_A = LIBRARY_NAME.lowercase() // Const 'val' initializer should be a constant value
fun main() {
    /**
     * companion object
     * Companion objects are parts of a class that allow you
     * to have properties and methods associated with that class.
     */
    val name = Library.LIBRARY_NAME
    println(name)

    val libraryNameB = LIBRARY_NAME.lowercase() // OK
    println(libraryNameB)
}