package cancelling_job

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

@OptIn(InternalCoroutinesApi::class)
fun main() = runBlocking {
    val job = launch {
        delay(5000)
        println("Start new job!")
    }

    delay(2000)
    job.cancel(cause = CancellationException("time is up!")) // cancelling a job because have a condition to cancel it
    println("Cancelling job...")
    if (job.isCancelled){
        println("Job is cancelled because ${job.getCancellationException().message}")
    }
}