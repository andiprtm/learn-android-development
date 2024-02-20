package modifier

// this is a public class, it can be accessed anywhere
open class Animal(
    public val name: String,
    protected val weight: Double,
    private val age: Int,
    private val isMammal: Boolean
) {
}