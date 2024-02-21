package abstract_class

abstract class Animal(var name: String, var weight: Double, var age: Int, var isCarnivore: Boolean) {
    open fun eat(){
        println("$name sedang makan !")
    }

    open fun sleep(){
        println("$name sedang tidur !")
    }
}