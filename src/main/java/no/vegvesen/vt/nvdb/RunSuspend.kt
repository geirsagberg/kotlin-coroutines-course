@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package no.vegvesen.vt.nvdb

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

fun main() {
    runSuspend {
        println("Hello, world!")
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
