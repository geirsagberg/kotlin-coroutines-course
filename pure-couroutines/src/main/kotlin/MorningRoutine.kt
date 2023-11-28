package net.sagberg

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    // Brew coffee
    val coffeeBrewing = brewCoffee()
    // Let out cat
    val catPeeing = letOutCat()
    // Brush teeth
    brushTeeth()
    // Wait for coffee and cat
    catPeeing.await()
    val coffee = coffeeBrewing.await()
    // Start working day
    startWorkingDay(coffee)
}

interface Async<T> {
    suspend fun await(): T
}

class AsyncResult<T>(private val callback: () -> T) : Async<T> {
    private var result: T? = null
    private var continuation: Continuation<T>? = null

    init {
        Thread.startVirtualThread {
            result = callback()
            continuation?.resume(result!!)
        }
    }

    override suspend fun await(): T = result ?: suspendCoroutine { cont ->
        continuation = cont
    }
}

data object Coffee

fun brewCoffee(): Async<Coffee> {
    println("Brewing coffee...")
    return AsyncResult {
        Thread.sleep(3000)
        println("Coffee done!")
        Coffee
    }
}

fun brushTeeth() {
    println("Brushing teeth...")
}

fun letOutCat(): Async<Unit> {
    println("Letting out cat...")
    return AsyncResult {
        Thread.sleep(6000)
        println("*meow meow*")
    }
}

fun startWorkingDay(coffee: Coffee) {
    println("Yum! $coffee")
}

