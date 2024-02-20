package property_delegation

fun main() {
    /**
     * property delegation
     * this is to simplify the set and get in each class.
     * you don't have to make boilerplate in every single class
     */
    val animal = Animal()
    animal.name = "Dicoding Miaw"
    println("Nama Hewan: ${animal.name}")

    val person = Person()
    person.name = "Dimas"
    println("Nama Orang: ${person.name}")

    val hero = Hero()
    hero.name = "Gatotkaca"
    println("Nama Pahlawan: ${hero.name}")
}