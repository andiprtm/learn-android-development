package run_with_also

fun main() {
    /**
     * run
     */
    val text = "Hello"
    val result = text.run {
        val from = this
        val to = this.replace("Hello", "Kotlin")
        "replace text from $from to $to"
    }
    println("result : $result")

    /**
     * with
     */
    val message = "Hello Kotlin!"
    val resultWith = with(message) {
        println("value is $this")
        println("with length ${this.length}")
    }

    /**
     * apply
     */
    // before
    val builder = StringBuilder()
    builder.append("Hello ")
    builder.append("Kotlin!")

    println(builder.toString())

    // after
    val messageApply = StringBuilder().apply {
        append("Hello ")
        append("Kotlin!")
    }

    println(messageApply.toString())
}