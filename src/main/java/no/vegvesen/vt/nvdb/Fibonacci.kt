package no.vegvesen.vt.nvdb

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
