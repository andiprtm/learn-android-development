package properties_in_kotlin_oop

fun main() {
    /**
     * properties in kotlin
     * by default kotlin are auto create getter function or setter function,
     * but you can override it if you want
     */
    val dicodingCat = Animal()
    println("Nama: ${dicodingCat.name}" )
    dicodingCat.name = "Goose"
    println("Nama: ${dicodingCat.name}")
    /*
        output:
            Nama: Kucing
            Nama: Goose
     */

    // this is after override it the setter and getter
    println("isMammal: ${dicodingCat.isMammal}" )
    dicodingCat.isMammal = false
    println("isMammal: ${dicodingCat.isMammal}")
}