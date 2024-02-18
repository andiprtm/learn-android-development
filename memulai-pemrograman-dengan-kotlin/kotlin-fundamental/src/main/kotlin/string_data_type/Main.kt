package string_data_type

fun main() {
    /**
     * string data type
     * This data type is like char but holds a group of characters, or commonly called a sentence
     * you can define the Type of variable to String
     * example :
     *     val sentence = "hi guys, how are you?"
     */

    val sentence = "Hi guys, how was your holiday?"

    /**
     * in string data type you can choose your specific character in your sentence with indexing
     * This concept is like using an array or collection, and you specifically select characters in the sentence using an index.
     * "index" is started from 0
     */
    val firstChar = sentence[6]

    println("The 4th character of the $sentence is $firstChar")
    /*
        output : The 4th character of the Hi guys, how was your holiday? is s
     */

    /**
     * int this data type you can also loop through strings as well
     */
    for (char in sentence){
        print("$char ")
    }
    println()
    /*
        output : H i   g u y s ,   h o w   w a s   y o u r   h o l i d a y ?
     */

    /**
     * Escaped String
     * This type of string literal is used if you are writing characters that will introduce ambiguity
     * Example:
     *     You want to write quotation marks in your sentence, maybe you want to emphasize a word or whatever
     *     if you write like this
     *         println("this boy is called "Single"")
     *     there will be ambiguity because inside the println function there are nested quotation marks
     *     to solve it, you can use this solution, namely \ (backslash), to skip the character
     */
    // println("this boy is called "Single"")
    /**
     * if you run the above statement, you will get this love message
     *     Unsupported [literal prefixes and suffixes]
     * instead use this
     */
    println("this boy is called \"Single\"")
    /*
        output : this boy is called "Single"
     */

    /**
     * here are some additional excaped strings
     *
     * \t: adds a tab to the text.
     * \n: creates a new line in the text.
     * \': adds a single quote character to the text.
     * \": adds a double quote character to the text.
     * \\: adds a backslash character to the text.
     *
     * it can be to translate a unicode like this
     *     println("this is translated of unicode \u00A9")
     */

    /**
     * Raw String
     * this literal string type is help you to write a multi line sentence
     */
    val line = """
        Line 1
        Line 2
        Line 3
        Line 4
    """.trimIndent()
    println(line)
    /*
        output:
            Line 1
            Line 2
            Line 3
            Line 4
     */
}