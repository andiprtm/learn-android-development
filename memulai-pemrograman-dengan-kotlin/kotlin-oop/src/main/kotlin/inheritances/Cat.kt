package inheritances

class Cat(pName: String, pWeight: Double, pAge: Int, pIsCarnivore: Boolean, val furColor: String, val numberOfFeet: Int): Animal(pName, pWeight, pAge, pIsCarnivore) {
    // add new behavior
    fun playWithHuman() {
        println("$name bermain bersama Manusia !")
    }

    // override the behavior of parent class
    override fun eat(){
        super.eat() // access the behavior of parent class
        println("$name sedang memakan ikan !")
    }

    // override the behavior of parent class
    override fun sleep() {
        println("$name sedang tidur di bantal !")
    }
}