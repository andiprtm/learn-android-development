package interfaces

abstract class Animal {
    //default value not allowed
    abstract val age: Int //must be overridden
    //default value allowed
    open val isEating = true //optional to be overridden
    val isRespire = true //cannot be overridden
    abstract fun run()
}