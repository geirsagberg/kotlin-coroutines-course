package net.sagberg.pure

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CompletableDeferred<T> {
    private var result: T? = null
    private var continuation: Continuation<T>? = null

    fun complete(value: T) {
        if (continuation != null) {
            // Someone is already waiting, let's give them the result
            continuation!!.resume(value)
        } else {
            // We are done, save the result until needed
            result = value
        }
    }

    suspend fun await(): T = when {
        result != null -> {
            // We have the result already, serve it up
            result!!
        }

        else -> {
            // We suspend the current function and resume once we have a result
            suspendCoroutine {
                continuation = it
            }
        }
    }
}
