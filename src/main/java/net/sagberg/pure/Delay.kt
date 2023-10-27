package net.sagberg.pure

import java.util.*

val delayTimer = Timer()

fun delay(timeMillis: Long, action: () -> Unit) {
    delayTimer.schedule(object : TimerTask() {
        override fun run() {
            action()
        }
    }, timeMillis)
}
