package class_in_kotlin_oop

fun main() {
    /**
     *  class in kotlin
     */
    val cat = Animal() // this is an object of animal class
    println("Nama: ${cat.name}, Berat: ${cat.weight}, Umur: ${cat.age}, mamalia: ${cat.isMammal}" )
    cat.eat()
    cat.sleep()
    /*
        output:
            Nama: Kucing, Berat: 3.2, Umur: 2, mamalia: true
            Kucing makan!
            Kucing tidur!
     */
}