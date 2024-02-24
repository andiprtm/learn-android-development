package fold_drop_and_take

fun main() {
    /**
     * fold.
     * to make a calculation for each item in the collection
     */
    val numbers = listOf(1, 2, 3)
    val fold = numbers.fold( 10) { current, item -> // 10 is initial value
        println("current $current")
        println("item $item")
        println()
        current + item
    }

    println("Fold result: $fold")

    /**
     * drop.
     * to delete an item
     */
    val number = listOf(1, 2, 3, 4, 5, 6)
    val drop = number.drop(3)

    println(drop)

    val dropLast = number.dropLast(3)

    println(dropLast)

    /**
     * take.
     * to take an item.
     */
    val total = listOf(1, 2, 3, 4, 5, 6)
    val take = total.take(3)

    println(take)

    val takeLast = total.takeLast(3)

    println(takeLast)
}