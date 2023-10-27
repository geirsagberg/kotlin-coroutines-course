package net.sagberg.pure

import java.util.*
import kotlin.coroutines.*

suspend fun main() {
    morningRoutine()
}

suspend fun morningRoutine() {
    val context = EmptyCoroutineContext

    val coffeeBrewing = CompletableDeferred<Coffee>()
    val catPeeing = CompletableDeferred<Unit>()

    // 1. Inline declaration
    suspend {
        println("Brewing coffee...")
        delay(3000) {
            println("Coffee is ready!")
            coffeeBrewing.complete(Coffee)
        }
    }.startCoroutine(Continuation(context) {})
    // 2. Creation of a suspend function
    catPeeingCoroutine(catPeeing)
        .startCoroutine(Continuation(context) {})

    brushTeeth()
    catPeeing.await()
    val coffee = coffeeBrewing.await()
    startWorkingDay(coffee)
}

class CompletableDeferred<T> {
    private var result: T? = null
    private var continuation: Continuation<T>? = null

    fun complete(value: T) {
        continuation?.resume(value) ?: run { result = value }
    }

    suspend fun await(): T {
        result?.let { return it }
        return suspendCoroutine { cont ->
            continuation = cont
        }
    }
}

fun catPeeingCoroutine(result: CompletableDeferred<Unit>): suspend () -> Unit = {
    println("Letting the cat out...")
    delay((Math.random() * 6000).toLong()) {
        println("Meow! *scratch* *scratch*")
        result.complete(Unit)
    }
}

val delayTimer = Timer()

fun delay(timeMillis: Long, action: () -> Unit) {
    delayTimer.schedule(object : TimerTask() {
        override fun run() {
            action()
            delayTimer.cancel()
        }
    }, timeMillis)
}

data object Coffee

fun brushTeeth() {}
fun startWorkingDay(coffee: Coffee) {
    println("Starting work with $coffee")
}
