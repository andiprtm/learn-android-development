package boolean_expressions

fun main() {
    /**
     * boolean expressions
     * this expression just contain 2 output, true or false
     * basically this expression has 3 operator
     *    && stand for AND operator
     *    || stand for OR operator
     *    ! stand fot NOT operator
     * not only that, there are some operator like more than, more than equal to, less than, less than equal to, and equal
     */

    //AND
    val officeOpen = 7
    val officeClosed = 16
    val now = 20

    val isOpen = now >= officeOpen && now <= officeClosed

    println("Office is open : $isOpen")
    /*
        output : Office is open : false
     */

    //OR
    val isClose = now < officeOpen || now > officeClosed

    println("Office is closed : $isClose")
    /*
        output : Office is closed : true
     */

    //NOT
    if (!isOpen) {
        print("Office is closed")
    } else {
        print("Office is open")
    }
    /*
        output : Office is closed
     */
}