package sequence

fun main() {
    /**
     * sequence.
     * this collection is lazy evaluation.
     * that means sequence just evaluated item that really needed
     */
    val list = (1..10000).toList()
    val number = list.asSequence().filter { it % 5 == 0 }.map { it * 2 }.first()
    println(number)

    // generate a collection
    val sequenceNumber = generateSequence(1) { it + 1 } // this will generate an infinite collection
    sequenceNumber.take(5).forEach { print("$it ") } // so we must use take function
}