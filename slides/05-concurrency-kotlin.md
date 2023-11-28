# Samtidighet i Kotlin

- `suspend fun`: denne funksjonen kan pause seg selv
- `coroutineScope`: skaper et scope med en context der coroutiner kan kjøre
- `async`: starter en asynkron job, returner en `Deferred<T>` som kan `await()`es

```kotlin
suspend fun morningRoutine() = coroutineScope {
  val coffeeBrewing = async {
    println("Brewing coffee...")
    delay(3.seconds)
    println("Coffee is ready!")
    Coffee
  }
  val catPeeing = async {
    println("Letting the cat out...")
    delay(Math.random() * 6.seconds)
    println("Meow! *scratch* *scratch*")
  }
  brushTeeth()
  catPeeing.await()
  val coffee = coffeeBrewing.await()
  startWorkingDay(coffee)
}
```

[Neste - Sekvensiell samtidighet](05b-concurrency-kotlin.md)
