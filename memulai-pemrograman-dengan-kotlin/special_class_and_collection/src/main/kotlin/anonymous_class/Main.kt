package anonymous_class

fun main() {
    /**
     * anonymous class
     * the class has no name, so we can create the object in the argument
     */
    flyWithWings(object : IFly {
        override fun fly() {
            TODO("Not yet implemented")
        }

    })
}

fun flyWithWings(bird: IFly) {
    bird.fly()
}