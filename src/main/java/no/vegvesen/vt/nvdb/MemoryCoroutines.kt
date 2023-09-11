package no.vegvesen.vt.nvdb

import kotlinx.coroutines.*

suspend fun main() {
    (1..10000).map {
        GlobalScope.launch {
            delay(1000)
            println("Hello from coroutine $it")
        }
    }.map { it.join() }
}
