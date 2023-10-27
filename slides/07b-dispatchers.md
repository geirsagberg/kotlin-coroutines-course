# Dispatchers

- Coroutines kjører på en eller flere tråder
- **Dispatchers** fra `kotlinx.coroutines` håndterer tråder og exception handling
- En **Dispatcher** er en avansert CoroutineContext
- `withContext` brukes for å endre CoroutineContext

## Dispatchers.Default

```kotlin
suspend fun doSomeCrunching() {
  withContext(Dispatchers.Default) {
    (1..1000).forEach { i ->
      launch {
        val prime = findPrime(i)
        println("Prime number $i is $prime")
      }
    }
  }
}
```

- Begrenser parallelitet til antall CPU-kjerner
- Best for tunge beregninger
- Default hvis man ikke spesifiserer en dispatcher

[DEMO](../src/main/java/net/sagberg/DispatchersDefault.kt)

## Dispatchers.IO

```kotlin
suspend fun fetchSomeData() {
  withContext(Dispatchers.IO) {
    val pageJobs = (1..100).map {
      async {
        findPage(it).also { println("Found page $it") }
      }
    }
    val pages = pageJobs.awaitAll()
    println("Pages: $pages")
  }
}
```

- Spinner opp nye tråder etter behov
- Best for operasjoner som venter på eksterne prosesser (f.eks. nettverkskall)

[DEMO](../src/main/java/net/sagberg/DispatchersIO.kt)

[Neste](08-sequence.md)
