package interfaces

class Camel(override val age: Int = 7, override val numberOfLeg: Int = 4) : Animal(), IWalk, IDrink {
    // this method must exist. from animal class
    override fun run() {
        println("Camel can run")
    }

    // this method must exist. from IWalk Interface
    override fun walk() {
        println("Camel can walk")
    }
}