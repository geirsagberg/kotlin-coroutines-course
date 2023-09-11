package no.vegvesen.vt.nvdb

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() {
    runBlocking {
        work()
    }
}

suspend fun work() {
    val report = requestReport()
    sendEmail(report)
    val pizza = orderPizza()
    eatPizza(pizza)
    relax()
}

suspend fun requestReport(): Report {
    delay(1.seconds)
    return Report
}

suspend fun sendEmail(report: Report) {
    delay(1.seconds)
    println("Sent email with report $report")
}

suspend fun orderPizza(): Pizza {
    delay(1.seconds)
    return Pizza
}

fun eatPizza(pizza: Pizza) {
    println("Yum! $pizza")
}

fun relax() {
    println("Ahh...")
}

data object Report

data object Pizza
