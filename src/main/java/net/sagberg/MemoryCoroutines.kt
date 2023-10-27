package net.sagberg

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    (1..10000).map {
        launch {
            delay(1000)
            println("Hello from coroutine $it")
        }
    }.joinAll()
}
