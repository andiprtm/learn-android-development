package while_and_do_while

fun main() {
    /**
     * while and do while
     * the different is while is running if and only if the condition is true
     * but, in do while if your condition is false, the do while still running just one time and execute only in do body
     */

    // while loop
    var counter = 8
    while (counter <= 7){
        println("Hello, World!")
        counter++
    }
    /*
        output:
     */

    // do while loop
    counter = 1
    do {
        println("Hello, World!")
        counter++
    } while (counter <= 7)
    /*
       output:
           Hello, World!
           Hello, World!
           Hello, World!
           Hello, World!
           Hello, World!
           Hello, World!
           Hello, World!
    */
}