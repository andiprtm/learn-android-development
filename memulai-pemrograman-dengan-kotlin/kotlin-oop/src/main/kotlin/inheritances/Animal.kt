package inheritances

open class Animal(val name: String, val weight: Double, val age: Int, val isCarnivore: Boolean) {
    open fun eat(){
        println("$name sedang makan!")
    }

    open fun sleep(){
        println("$name sedang tidur!")
    }
}