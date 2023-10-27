package net.sagberg

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun wordsFlow(text: String): Flow<String> = flow {
    // Split the string into words based on spaces
    val words = text.split(" ")

    // Emit each word with a delay
    for (word in words) {
        delay(500)
        emit(word)
    }
}

fun main() {
    runBlocking {
        val sentence = "Hello Bekk! Flows are fun and easy."
        wordsFlow(sentence).collect { word ->
            println(word)
        }
        wordsFlow(sentence).take(3).toList().let { println(it) }
    }
}
