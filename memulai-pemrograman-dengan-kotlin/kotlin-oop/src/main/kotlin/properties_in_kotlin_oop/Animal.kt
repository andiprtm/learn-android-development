package properties_in_kotlin_oop

class Animal() {
    /**
     * declaration of get and set must be after that variable
     */
    var name: String = "Kucing"
    var weight: Double = 3.2
    var age: Int = 2
    var isMammal: Boolean = true

        get(){
            println("Fungsi Getter terpanggil")
            return field
        }
        set(value){
            println("Fungsi Setter terpanggil")
            field = value
        }

    fun eat(){
        println("$name makan!")
    }

    fun sleep() {
        println("$name tidur!")
    }
}