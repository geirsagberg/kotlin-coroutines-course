package net.sagberg

import kotlin.coroutines.*

// A simple mock asynchronous function to mimic a delay, like a network call
fun asyncMockDelay(timeMillis: Long, callback: (String) -> Unit) {
    Thread {
        Thread.sleep(timeMillis)
        callback("Data fetched after $timeMillis ms")
    }.start()
}

// Using suspendCoroutine to wrap the asyncMockDelay function
suspend fun fetchAfterDelay(timeMillis: Long): String = suspendCoroutine { continuation ->
    asyncMockDelay(timeMillis) { result ->
        continuation.resume(result)
    }
}

fun main() {
    // Sequence the asynchronous calls using coroutines
    ::fetchDataSequence.startCoroutine(Continuation(EmptyCoroutineContext) {
        println("All data fetched!")
    })
}

suspend fun fetchDataSequence() {
    val data1 = fetchAfterDelay(1000)
    println("Received: $data1")

    val data2 = fetchAfterDelay(500)
    println("Received: $data2")

    val data3 = fetchAfterDelay(1500)
    println("Received: $data3")
}
