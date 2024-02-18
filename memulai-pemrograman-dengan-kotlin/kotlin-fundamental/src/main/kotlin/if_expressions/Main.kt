package if_expressions

fun main() {
    /**
     * if expressions
     * basically this is used to make a decision
     * and this expressions return boolean, therefore it can be assigned in variable
     *
     * note : kotlin not support for ternary operation
     */
    val openHours = 7
    val now = 7
    val office: String = if (now > 7) {
        "Office already open"
    } else if (now == openHours){
        "Wait a minute, office will be open"
    } else {
        "Office is closed"
    }

    print(office)
    /*
        output : Wait a minute, office will be open
     */
}