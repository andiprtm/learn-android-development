package expressions_and_statements

fun main() {
    /**
     * expressions and statements
     *
     * expressions is the term for something that returns a value
     * statement is the term for something that does not return any value
     */
    val now = 2
    val openOffice = 7

    //statement
    if (now > openOffice) {
        println("Office already open")
    } else {
        println("Office close")
    }

    //expressions
    val office = if (now > openOffice) "Office already open" else "Office close"

    print(office)
}