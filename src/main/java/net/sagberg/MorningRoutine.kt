package net.sagberg

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.times

data object Coffee

suspend fun main() {
    morningRoutine()
}

suspend fun morningRoutine() = coroutineScope {
    val coffeeBrewing = async {
        delay(3.seconds)
        println("Coffee is ready")
        Coffee
    }
    val catPeeing = async {
        delay(Math.random() * 6.seconds)
        println("Meow! *scratch* *scratch*")
    }
    brushTeeth()
    catPeeing.await()
    val coffee = coffeeBrewing.await()
    startWorkingDay(coffee)
}

fun startWorkingDay(coffee: Coffee) {
    println("Starting work with $coffee")
}

fun brushTeeth() {
    println("Brushing teeth")
}
