package net.sagberg.pure

import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    morningRoutine()
}

suspend fun morningRoutine() {
    val coffeeBrewing = brewCoffee()
    val catPeeing = letOutCat()

    brushTeeth()
    catPeeing.await()
    val coffee = coffeeBrewing.await()
    startWorkingDay(coffee)
}

fun brewCoffee(): CompletableDeferred<Coffee> {
    val coffeeBrewing = CompletableDeferred<Coffee>()
    println("Brewing coffee...")
    delay(3000) {
        println("Coffee is ready!")
        coffeeBrewing.complete(Coffee)
    }
    return coffeeBrewing
}

fun letOutCat(): CompletableDeferred<Unit> {
    val catPeeing = CompletableDeferred<Unit>()
    println("Letting the cat out...")
    delay((Math.random() * 6000).toLong()) {
        println("Meow! *scratch* *scratch*")
        catPeeing.complete(Unit)
    }
    return catPeeing
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

val delayTimer = Timer()

fun delay(timeMillis: Long, action: () -> Unit) {
    delayTimer.schedule(object : TimerTask() {
        override fun run() {
            action()
        }
    }, timeMillis)
}

data object Coffee

fun brushTeeth() {
    println("Brushing teeth...")
}

fun startWorkingDay(coffee: Coffee) {
    println("Starting work with $coffee")
}
