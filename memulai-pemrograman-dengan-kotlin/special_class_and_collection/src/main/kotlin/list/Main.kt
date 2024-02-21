package list

fun main() {
    /**
     * list
     * this collection is a group of data
     * and list cannot mutable
     */
    val anyList = listOf('a', "Kotlin", 3, true, User())
    println(anyList[3])

    /**
     * mutable list
     * the mutable list is mutable version of list
     */
    val anyListMutable = mutableListOf('a', "Kotlin", 3, true, User())
    anyListMutable.add('d') // menambah item di akhir list
    anyListMutable.add(1, "love") // menambah item pada indeks ke-1
    anyListMutable[3] = false // mengubah nilai item pada indeks ke-3
    anyListMutable.removeAt(0) // menghapus item pada indeks ke-0

    /**
     * array list
     * is an alternative of mutable list
     */
    val arrayList = arrayListOf("Kotlin", "Java")
    arrayList[0] = "Dart" // no error
    arrayList.add("JavaScript") // no error
}