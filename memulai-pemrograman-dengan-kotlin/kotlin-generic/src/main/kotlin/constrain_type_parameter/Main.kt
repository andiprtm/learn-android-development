package constrain_type_parameter

fun main() {
    /**
     * constrain type parameter
     * This is used to create constraints on the type of parameters in the class or in the function
     */

    // constrain type parameter in function
    val numbers = listOf(1, 2, 3, 4, 5)
    numbers.sumNumber()
    val names = listOf("dicoding", "academy")
    // names.sumNumber() // error : inferred type String is not a subtype of Number

    // constrain type parameter in class
    val numbersClass = ListNumber<Long>()
    val numbers2 = ListNumber<Int>()
    // val numbers3 = ListNumber<String>() // error: Type argument is not within its bounds
}

fun <T : Number> List<T>.sumNumber(): T {
    var sum = 0.0
    for (number in this) {
        sum += number.toDouble()
    }
    return sum.toInt() as T
}

class ListNumber<T : Number> : List<T>{
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun containsAll(elements: Collection<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): T {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<T> {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<T> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<T> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: T): Int {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: T): Int {
        TODO("Not yet implemented")
    }
}