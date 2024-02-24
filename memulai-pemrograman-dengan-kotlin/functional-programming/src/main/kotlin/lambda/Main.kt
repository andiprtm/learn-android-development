package lambda


fun main() {
    /**
     * lambda expression
     */

    // anonymous class with lambda
    val comparator = Runnable {

    }

    // function with lambda
    val length = messageLength("Hello From lambda")
    println("Message length $length")

    // looping with lambda
    val ranges = 1.rangeTo(10) step 3
    ranges.forEachIndexed { index, _ ->
        println("index $index")
    }
    /*
       output :
           index 0
           index 1
           index 2
           index 3
    */
}

// regular function
fun getMessage(name: String): String {
    return "Hello $name"
}

// function with lambda
val message :(String) -> String = { name: String ->
    "Hello $name"
}

// function with lambda
val messageLength = { message: String -> message.length }