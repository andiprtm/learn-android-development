package create_and_run_job

import kotlinx.coroutines.*

fun main() = runBlocking {

    // use launch toi initiate a job
    val job = launch {
        delay(1000L)
        println("Start new job!")
    }

    // use Job()
    val job2 = Job()

    // lazy start job
    val job3 = launch(start = CoroutineStart.LAZY) {
        TODO("Not implemented yet!")
    }

    // to start a job
    /**
     * with start
     * if you use start, a job will activate at that time
     */
    job.start()
    /**
     * with join
     * if you use join, a job will activate since the job is done execute
     */
    // job.join()
    println("Other task")
}