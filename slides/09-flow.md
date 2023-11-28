# Flow

- Kald asynkron strøm av verdier
- Startes med `flow`, sender verdier med `emit`, henter verdier med `collect`
- Kan komponeres (som `Flux`)
- Kan bruke `suspend` functions
- Kan jobbes med utenfor en coroutine, men `collect` må gjøres i en coroutine

```kotlin
fun wordsFlow(text: String): Flow<String> = flow {
  // Split the string into words based on spaces
  val words = text.split(" ")

  // Emit each word with a delay
  for (word in words) {
    delay(500)
    emit(word)
  }
}
```

[DEMO](../src/main/java/net/sagberg/WordFlow.kt)

[Neste - Ressurser](10-ressurser.md)
