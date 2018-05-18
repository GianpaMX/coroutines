package io.github.gianpamx

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
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

    private fun sumOf1To(n: Int) = (n / 2) * (n + 1)

    @Ignore("Test finishes while some coroutines haven't")
    @Test
    fun aLotOfCoroutines() {
        val n = 1_000_000
        val c = AtomicInteger()

        for (i in 1..n)
            launch {
                c.addAndGet(i)
            }

        assertThat(c.get(), IsEqual(sumOf1To(n)))
    }

    @Test
    fun waitForALotOfCoroutines() {
        val n = 1_000_000

        val deferred = (1..n).map { i ->
            async {
                i
            }
        }

        val sum = runBlocking {
            deferred.sumBy { it.await() }
        }

        assertThat(sum, IsEqual(sumOf1To(n)))
    }
}
