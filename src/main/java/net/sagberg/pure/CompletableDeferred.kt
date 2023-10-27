package net.sagberg.pure

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CompletableDeferred<T> {
    private var result: T? = null
    private var continuation: Continuation<T>? = null

    fun complete(value: T) {
        continuation?.resume(value) ?: run { result = value }
    }

    suspend fun await(): T {
        result?.let { return it }
        return suspendCoroutine { cont ->
            continuation = cont
        }
    }
}
