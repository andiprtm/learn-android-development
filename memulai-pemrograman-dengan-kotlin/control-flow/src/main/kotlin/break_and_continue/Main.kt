package break_and_continue

fun main() {
    /**
     * break and continue
     * break used to stop loop processed without continued the loop
     * continue used to stop running loop to continue the next iteration
     */

    // continue
    val listOfInt = listOf(1, 2, 3, null, 5, null, 7)

    for (i in listOfInt) {
        if (i == null) continue
        print(i)
    }
    /*
        output: 12357
    */

    //break
    loop@ for (i in 1..10) {
        println("Outside Loop")

        for (j in 1..10) {
            println("Inside Loop")
            if ( j > 5) break@loop
        }
    }
    /*
       output :
           Outside Loop
           Inside Loop
           Inside Loop
           Inside Loop
           Inside Loop
           Inside Loop
           Inside Loop
    */
}