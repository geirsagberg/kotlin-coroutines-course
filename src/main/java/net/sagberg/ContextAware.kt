package net.sagberg

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class MyContext(val name: String) : AbstractCoroutineContextElement(MyContext) {
    companion object Key : CoroutineContext.Key<MyContext>
}

suspend fun main() {
    val contextA = MyContext("First")
    val contextB = MyContext("Second")

    coroutineScope {
        println("Current context: ${coroutineContext[MyContext]?.name}")

        withContext(contextA) {
            println("Current context: ${coroutineContext[MyContext]?.name}")

            withContext(contextB) {
                println("Current context: ${coroutineContext[MyContext]?.name}")
            }

            println("Current context: ${coroutineContext[MyContext]?.name}")
        }

        println("Current context: ${coroutineContext[MyContext]?.name}")
    }
}
