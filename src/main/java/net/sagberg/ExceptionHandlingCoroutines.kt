package net.sagberg

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    val job = launch {
        throw RuntimeException("Error in coroutine")
    }

    job.invokeOnCompletion { e ->
        if (e is RuntimeException) {
            println("Caught exception from coroutine: ${e.message}")
        }
    }

    println("Main coroutine continues running.")

    delay(100)  // Give some time for the child coroutine to complete and handle the exception
}
