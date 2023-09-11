package no.vegvesen.vt.nvdb

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.times

data object Coffee

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


suspend fun main() {
    morningRoutine()
}
