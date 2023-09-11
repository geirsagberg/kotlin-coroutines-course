package no.vegvesen.vt.nvdb

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        doSomeCrunching()
    }
}

suspend fun doSomeCrunching() {
    withContext(Dispatchers.Default) {
        (1..1000).forEach { i ->
            launch {
                val prime = findPrime(i)
                println("Prime number $i is $prime")
            }
        }
    }
}

fun findPrime(i: Int): Int {
    Thread.sleep(1000)
    return i
}
