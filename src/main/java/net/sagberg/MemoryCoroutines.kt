package net.sagberg

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        (1..10000).map {
            launch {
                delay(1000)
                println("Hello from coroutine $it")
            }
        }.joinAll()
    }
}
