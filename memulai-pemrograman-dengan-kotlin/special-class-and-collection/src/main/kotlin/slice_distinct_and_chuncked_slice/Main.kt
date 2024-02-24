package slice_distinct_and_chuncked_slice

import java.util.*

data class Item(val key: String, val value: Any)
fun main() {
    /**
     * slice
     * to slice a collection according to your condition
     */
    val total = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var slice = total.slice(3..6)

    println(slice)

    // slice with a step
    slice = total.slice(3..6 step 2)

    println(slice)

    // slice with index
    val index = listOf(2, 3, 5, 8)
    slice = total.slice(index)

    println(slice)

    /**
     * distinct
     * like a set, to remove duplicate value
     * different with the set is, this method can apply in class or data class
     */
    val items = listOf(
        Item("1", "Kotlin"),
        Item("2", "is"),
        Item("3", "Awesome"),
        Item("3", "as"),
        Item("3", "Programming"),
        Item("3", "Language")
    )

    val distinctItems = items.distinctBy { it.key }
    distinctItems.forEach {
        println("${it.key} with value ${it.value}")
    }
    /*
        output:
            1 with value Kotlin
            2 with value is
            3 with value Awesome
    */

    // distinct by length
    val text = listOf("A", "B", "CC", "DD", "EEE", "F", "GGGG")
    val distinct = text.distinctBy {
        it.length
    }

    println(distinct)

    /**
     * chunked.
     * to chunk (cacah)
     */
    val word = "QWERTY"
    val chunkedTransform = word.chunked(3) {
        it.toString().lowercase(Locale.getDefault())
    }

    println(chunkedTransform)

}