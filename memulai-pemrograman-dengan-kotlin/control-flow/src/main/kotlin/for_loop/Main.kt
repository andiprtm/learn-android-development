package for_loop

fun main() {
    /**
     * for loop
     * foor loop is used to loop through a command
     */

    // standard for loop
    var ranges = 1..5
    for (i in ranges){
        println("value is $i!")
    }
    /*
       output :
           value is 1!
           value is 2!
           value is 3!
           value is 4!
           value is 5!
    */

    // standard for loop alternative with range (replacement of .. )
    ranges = 1.rangeTo(5)
    for (i in ranges){
        println("value is $i!")
    }
    /*
       output :
           value is 1!
           value is 2!
           value is 3!
           value is 4!
           value is 5!
    */

    // for loop with access to index using withIndex() function
    val rangesStep = 1.rangeTo(10) step 3
    for ((index, value) in rangesStep.withIndex()) {
        println("value $value with index $index")
    }
    /*
        output:
            value 1 with index 0
            value 4 with index 1
            value 7 with index 2
            value 10 with index 3
     */

    // for loop with lambda expression ( -> )
    rangesStep.forEachIndexed { index, value ->
        println("value is $value! in index $index")
    }
    /*
        output:
            value is 1! in index 0
            value is 4! in index 1
            value is 7! in index 2
            value is 10! in index 3
     */
}