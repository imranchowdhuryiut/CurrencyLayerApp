package com.imran.currencyapp.helper

import com.imran.currencyapp.data.api.network.AppExecutor
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class SameThreadExecutorService : AbstractExecutorService() {
    //volatile because can be viewed by other threads
    @Volatile
    private var terminated = false
    override fun shutdown() {
        terminated = true
    }

    override fun isShutdown(): Boolean {
        return terminated
    }

    override fun isTerminated(): Boolean {
        return terminated
    }

    @Throws(InterruptedException::class)
    override fun awaitTermination(theTimeout: Long, theUnit: TimeUnit?): Boolean {
        shutdown()
        return terminated
    }

    override fun shutdownNow(): List<Runnable> {
        return emptyList()
    }

    override fun execute(theCommand: Runnable) {
        theCommand.run()
    }

    companion object {
        fun executor(): AppExecutor {
            val executor = SameThreadExecutorService()
            return object : AppExecutor {

                override fun ioThread(f: () -> Unit) {
                    executor.execute { f.invoke() }
                }

                override fun networkThread(f: () -> Unit) {
                    executor.execute { f.invoke() }
                }

                override fun mainThread(f: () -> Unit) {
                    executor.execute { f.invoke() }
                }

            }
        }
    }
}