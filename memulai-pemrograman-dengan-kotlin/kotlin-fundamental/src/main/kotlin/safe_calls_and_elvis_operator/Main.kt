package safe_calls_and_elvis_operator

fun main() {
    /**
     * safe calls and elvis operator
     * This operator is a simpler way to safely access variables
     */

    val text: String? = null
    text?.length
    /**
     * with ?. operator, if variable has null value then the operation will be skipped by compiler
     * if you want to give the variable default value, use elvis operator instead
     */

    val word: String? = null
    val textLength = word?.length ?: 7
    /**
     * if the variable is null then the default value has returned
     */

    // it same like this
    val wordlenght = if (word != null) word.length else 7

    /**
     * kotlin have one more operator to handle nullable, that is non-null assertion
     * his operator is force the variable to non-null, though the value is null
     */
    val word2: String? = null
    // val wordlenght2 = text!!.length //not recommended
}