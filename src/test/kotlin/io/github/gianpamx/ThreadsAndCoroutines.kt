package io.github.gianpamx

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

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

    @Test
    fun blockingInsteadOfThreadSleep() {
        println("Start")

        // Start a coroutine
        launch {
            delay(1000)
            println("Hello")
        }

        runBlocking {
            delay(2000) // wait for 2 seconds
        }
        println("Stop")
    }

    @Ignore("My patience runs out")
    @Test(timeout = 5_000)
    fun aLotOfThreads() {
        val c = AtomicInteger()

        for (i in 1..1_000_000)
            thread(start = true) {
                c.addAndGet(i)
            }

        println(c.get())
    }
}
