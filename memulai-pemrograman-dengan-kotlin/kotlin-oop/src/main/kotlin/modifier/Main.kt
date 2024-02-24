package modifier

fun main() {
    /**
     * Here are the kinds of access rights and a brief explanation that can be used in Kotlin:
     *
     * Public: The access right with the broadest scope. Members with this modifier can be accessed from anywhere.
     *
     * Private: The access right with the most limited scope. Members to which it applies can only be accessed within the same scope. Usually in the same file
     *
     * Protected: Access rights whose scope is limited to the class hierarchy. Members can only be accessed on their child classes or the class itself.
     *
     * Internal: An access right whose scope is limited to a single module. Members who use it cannot be accessed outside of that module. Usually in the same folder
     */

    val dicodingCat = Animal("Dicoding Miaw", 2.5, 2, true)
    // cannot access the private properties in animal, because it private. just can access in the same file
    // println("Nama: ${dicodingCat.name}, Berat: ${dicodingCat.weight}, Umur: ${dicodingCat.age}, mamalia: ${dicodingCat.isMammal}")

    val cat = Cat("Dicoding Miaw", 2.0, 3, true )
    println("Nama Kucing: ${cat.name}")
    // println("Berat Kucing: ${cat.weight}") // error: expecting a top level declaration
}