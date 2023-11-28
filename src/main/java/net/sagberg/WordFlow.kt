package net.sagberg

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

fun wordsFlow(text: String): Flow<String> = flow {
    // Split the string into words based on spaces
    val words = text.split(" ")

    // Emit each word with a delay
    for (word in words) {
        delay(500)
        emit(word)
    }
}

suspend fun main() {
    val sentence = "Hello JavaBin! Flows are fun and easy."
    wordsFlow(sentence).collect { word ->
        println(word)
    }
    wordsFlow(sentence).take(5).toList().let { println(it) }
}
