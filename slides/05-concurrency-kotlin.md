# Samtidighet i Kotlin

- `suspend fun`: denne funksjonen kan pause seg selv
- `coroutineScope`: skaper et scope med en context der coroutiner kan kjøre
- `async`: starter en asynkron job, returner en `Deferred<T>` som kan `await()`es

```kotlin
suspend fun morningRoutine() {
    coroutineScope {
        val coffeeBrewing = async {
            delay(30.seconds)
            Coffee
        }
        val catPeeing = async {
            delay(10.seconds + Math.random() * 60.seconds)
        }
        brushTeeth()
        catPeeing.await()
        val coffee = coffeeBrewing.await()
        startWorkingDay(coffee)
    }
}
```

[Neste](05b-concurrency-kotlin.md)
