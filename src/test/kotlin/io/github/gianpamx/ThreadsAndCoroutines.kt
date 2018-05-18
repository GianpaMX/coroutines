package io.github.gianpamx

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ThreadsAndCoroutines {
    @Test
    fun myFirstCoroutine() {
        println("Start")

        // Start a coroutine
        launch {
            delay(1000)
            println("Hello")
        }

        Thread.sleep(2000) // wait for 2 seconds
        println("Stop")
    }
}
