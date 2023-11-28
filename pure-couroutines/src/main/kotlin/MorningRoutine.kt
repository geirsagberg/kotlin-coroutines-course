package net.sagberg

data object Coffee

fun brushTeeth() {
    println("Brushing teeth...")
}

fun startWorkingDay(coffee: Coffee) {
    println("Starting work with $coffee")
}

suspend fun main() {
    // Brew coffee
    // Let out cat
    // Brush teeth
    // Wait for coffee and cat
    // Start working day
}
