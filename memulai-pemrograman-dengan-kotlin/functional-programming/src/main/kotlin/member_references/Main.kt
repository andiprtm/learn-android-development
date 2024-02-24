package member_references

var message = "Kotlin"
fun main() {
    /**
     * member references
     * with this you can reference the lambda expressions as an instances
     */

    // Function References
    val numbers = 1.rangeTo(10)
    val evenNumbers = numbers.filter(::isEvenNumber)

    // alternative
    // val evenNumbers = numbers.filter(Int::isEvenNumber)

    println(evenNumbers)

    // Property References
    println(::message.name)
    println(::message.get())

    ::message.set("Kotlin Academy") // this is only if the object uses var keyword

    println(::message.get())
}

// the count is reference in a sum
val sum: (Int, Int) -> Int = ::count
fun count(valueA: Int, valueB
: Int): Int {
    return valueA + valueB
}
fun isEvenNumber(number: Int) = number % 2 == 0

// alternative
// fun Int.isEvenNumber() = this % 2 == 0