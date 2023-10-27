package net.sagberg.pure

data object Coffee

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

fun brushTeeth() {
    println("Brushing teeth...")
}

fun startWorkingDay(coffee: Coffee) {
    println("Starting work with $coffee")
}
