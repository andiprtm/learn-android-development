package map

fun main() {
    /**
     * map
     * this is a key-value collection
     */
    val capital = mapOf(
        "Jakarta" to "Indonesia",
        "London" to "England",
        "New Delhi" to "India"
    )
    println(capital["Jakarta"])
    // Output: Indonesia

    println(capital.getValue("Jakarta"))
    // Output: Indonesia

    /*
        this is different between them
     */
    println(capital["Amsterdam"])
    // Output: null

    println(capital.getValue("Amsterdam"))
    // Output: Exception in thread "main" java.util.NoSuchElementException: Key Amsterdam is missing in the map.

    /*
        print the keys
     */
    val mapKeys = capital.keys
    // mapKeys: [Jakarta, London, New Delhi]

    /*
        print the values
     */
    val mapValues = capital.values
    // mapValues: [Indonesia, England, India]

    /**
     * mutable in a map is not recommended
     * if you really needed is ok, use as rarely as possible
     */
    val mutableCapital = capital.toMutableMap()
    mutableCapital["Amsterdam"] = "Netherlands"
    mutableCapital["Berlin"] = "Germany"

    // alternative reassignment
    mutableCapital.put("Amsterdam", "Netherlands")
    mutableCapital.put("Berlin", "Germany")
}