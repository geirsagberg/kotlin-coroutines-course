package no.vegvesen.vt.nvdb

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        fetchSomeData()
    }
}

suspend fun fetchSomeData() {
    withContext(Dispatchers.IO.limitedParallelism(16)) {
        val pageJobs = (1..100).map {
            async {
                findPage(it).also { println("Found page $it") }
            }
        }
        val pages = pageJobs.awaitAll()
        println("Pages: $pages")
    }
}

fun findPage(it: Int): String {
    Thread.sleep((Math.random() * 1000).toLong())
    return it.toString()
}
