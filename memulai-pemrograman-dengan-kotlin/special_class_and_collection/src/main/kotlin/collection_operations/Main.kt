package collection_operations

fun main() {
    /**
     * filter.
     * filter according to the conditions you want
     */
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val evenList = numberList.filter { it % 2 == 0 }

    /**
     * filter not.
     * negation of filter
     */
    val numberListFilterNot = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val notEvenList = numberListFilterNot.filterNot { it % 2 == 0 }

    /**
     * map.
     * to make a new map collection
     */
    val numberListMap = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val multipliedBy5 = numberListMap.map { it * 5 } // "it" will present each number on numberListMap

    /**
     * count.
     * use to count item
     */
    val numberListCount = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(numberList.count())

    /**
     * find(), firstOrNull(), & lastOrNull()
     * find and firstOrNull it almost same to find an item according your condition
     * lasOrNull used to find item from the back/last item to first item
     */
    val numberListFind = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val firstOddNumber = numberListFind.find { it % 2 == 1 }
    val lastOrNullNumber = numberListFind.lastOrNull { it % 2 == 0 }
    println(lastOrNullNumber)

    /**
     * first and last.
     * used to find item which is first item or last item
     */
    val numberListFirstOrLast = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(numberListFirstOrLast.first { it > 5 })
    println(numberListFirstOrLast.last { it > 5 })

    /**
     * sum.
     * to sum all item
     */
    val numberListSum = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val total = numberListSum.sum()

    /**
     * sorted.
     * to sorting item.
     * the default sort is ascending.
     */
    // ascending sort
    val kotlinCharAsc = listOf('k', 'o', 't', 'l', 'i', 'n')
    val ascendingSort = kotlinCharAsc.sorted()
    println(ascendingSort)

    // descending sort
    val kotlinCharDesc = listOf('k', 'o', 't', 'l', 'i', 'n')
    val descendingSort = kotlinCharDesc.sortedDescending()
    println(descendingSort)
}