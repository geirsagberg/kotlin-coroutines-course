# `suspend fun main`

- Man kan gjøre entrypointet til en `suspend` function:

```kotlin
suspend fun main(args: Array<String>) {
    // ...
}
```

- Kotlin lager en syntetisk main-metode:

```kotlin
fun main(args: Array<String>) {
    runSuspend {
        main(args) // calling the suspend version
    }
}

internal fun runSuspend(block: suspend () -> Unit) {
  val run = RunSuspend()
  block.startCoroutine(run)
  run.await()
}

private class RunSuspend : Continuation<Unit> {
  override val context: CoroutineContext
    get() = EmptyCoroutineContext

  var result: Result<Unit>? = null

  override fun resumeWith(result: Result<Unit>) = synchronized(this) {
    this.result = result
    (this as Object).notifyAll()
  }

  fun await() = synchronized(this) {
    while (true) {
      when (val result = this.result) {
        null -> (this as Object).wait()
        else -> {
          result.getOrThrow() // throw up failure
          return
        }
      }
    }
  }
}
```

[Neste](07-context.md)
