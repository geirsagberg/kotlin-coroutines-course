# kotlinx.coroutines

- Mesteparten av funksjonaliteten som `coroutineScope`, `Job`, `Dispatcher`, `async` og `launch` kommer fra et ekstra bibliotek: `kotlinx.coroutines`.
- Bare `suspend`, `Continuation` og `CoroutineContext` følger med Kotlin ut av boksen.

**DEMO:**
- [kotlinx.coroutines](../src/main/java/no/vegvesen/vt/nvdb/MorningRoutine.kt)
- [kotlin.coroutines](../src/main/java/no/vegvesen/vt/nvdb/pure/MorningRoutine.kt)

[Neste](06b-suspend-fun-main.md)