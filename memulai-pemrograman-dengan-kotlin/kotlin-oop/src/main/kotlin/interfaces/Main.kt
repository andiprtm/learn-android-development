package interfaces

fun main() {
    /**
     * interface.
     * interface is used if you don't know what is the default value, and what the logic of the behavior
     * therefore interface is mandatory to override all attributes or behavior/function
     */
    val camel = Camel()
    camel.walk()
    camel.run()
    /*
        output:
            Camel can walk
            Camel can run
     */
}