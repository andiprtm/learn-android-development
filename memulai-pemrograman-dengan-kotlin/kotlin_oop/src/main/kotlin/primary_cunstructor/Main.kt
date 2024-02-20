package primary_cunstructor

fun main() {
    /**
     * primary constructor
     * in default kotlin has provided the constructor when you create an argument in the class
     * also you can have default value in the argument that have you been created
     */
    val dicodingCat = Animal("Dicoding Miaw", 4.2, 2, true)
    println("Nama: ${dicodingCat.name}, Berat: ${dicodingCat.weight}, Umur: ${dicodingCat.age}, mamalia: ${dicodingCat.isMammal}")
}