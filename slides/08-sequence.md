# Sequence

- Man kan starte en lazy generering med `sequence`
- Den gir fra seg et resultat og pauser ved `yield`
- Kjører synkront
- Kan ikke kalle vilkårlige `suspend fun`

```kotlin
fun fibonacci(): Sequence<Long> = sequence {
  var pair = Pair(0L, 1L)
  while (true) {
    yield(pair.first)
    pair = Pair(pair.second, pair.first + pair.second)
  }
}

fun main() {
  // Print the first 10 Fibonacci numbers
  fibonacci().take(10).forEach { println(it) }
}
```

[DEMO](../src/main/java/net/sagberg/Fibonacci.kt)

[Neste - Flow](09-flow.md)
