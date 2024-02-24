package start_coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(1000)
        println("Gassss")
    }
    println("helloo")
    delay(2000)
    /*
        output:
            helloo
            Gassss
     */
}