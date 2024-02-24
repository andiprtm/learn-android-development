package set

fun main() {
    /**
     * set
     * this collection just contain unique value with a same type
     */
    val integerSet = setOf(1, 2, 4, 2, 1, 5)
    val setA = integerSet
    val setC = setOf(1, 5, 7)
    println(integerSet)
    /*
        output: [1, 2, 4, 5] // the same value will be removed
     */

    // keyword in
    println(5 in integerSet)
    /*
        output: true
     */

    // intersect
    val intersect = setA.intersect(setC)
    println(intersect)
    /*
        output: [1, 5]
     */

    // union
    val union = setA.union(setC)
    println(union)
    /*
        output: [1, 2, 4, 5, 7]
     */

    //symmetric difference
    val numbers = setOf("one", "two", "three")
    val numbers2 = setOf("three", "four")

    // merge differences
    println((numbers - numbers2) union (numbers2 - numbers))

    /**
     * mutable set
     * In Mutable Set, you can add and delete items but cannot change the value like in List.
     */
    val mutableSet = mutableSetOf(1, 2, 4, 2, 1, 5)
    //mutableSet[2] = 6 // tidak bisa mengubah mutableSet
    mutableSet.add(6) // menambah item di akhir set
    mutableSet.remove(1) //menghapus item yang memiliki nilai 1
}