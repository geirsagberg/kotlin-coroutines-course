package net.sagberg

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    // Brew coffee
    val coffeeBrewing = async {
        println("Brewing coffee...")
        Thread.sleep(3000)
        println("Coffee done!")
        Coffee
    }
    // Let out cat
    val catPeeing = async {
        println("Letting out cat...")
        Thread.sleep(6000)
        println("*meow meow*")
    }
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

fun <T> async(callback: () -> T): Async<T> {
    return AsyncResult(callback)
}

// Scenario 1: callback completes before await -> await returns immediately
// Scenario 2: callback does not complete before await -> await suspends, providing a continuation, which is resumed when callback completes

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
    return AsyncResult {
        println("Brewing coffee...")
        Thread.sleep(3000)
        println("Coffee done!")
        Coffee
    }
}

fun brushTeeth() {
    println("Brushing teeth...")
}

fun letOutCat(): Async<Unit> {
    return AsyncResult {
        println("Letting out cat...")
        Thread.sleep(6000)
        println("*meow meow*")
    }
}

fun startWorkingDay(coffee: Coffee) {
    println("Yum! $coffee")
}

