package function_interface

import anonymous_class.IFly

fun flyWithWings(bird: IFly2){
    bird.fly()
}
fun main() {
    /**
     * function interface
     *  makes the anonymous class implementation simpler and more readable
     */
    flyWithWings {
        println("The Bird flying")
    }
}

