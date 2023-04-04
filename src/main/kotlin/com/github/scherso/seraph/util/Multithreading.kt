package com.github.scherso.seraph.util

import com.github.scherso.seraph.NAME
import com.google.common.util.concurrent.ThreadFactoryBuilder
import java.util.concurrent.*

class Multithreading {

    /**
     * Thread pool for the scheduler.
     *
     * @see ExecutorService
     */
    private val pool: ExecutorService =
        Executors.newCachedThreadPool(ThreadFactoryBuilder().setNameFormat("$NAME-%d").build())

    /**
     * Scheduled executor service for the scheduler.
     *
     * @see ScheduledExecutorService
     */
    private val runnable: ScheduledExecutorService =
        ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1)

    /**
     * @param runnable Runnable to be executed
     * @param delay    Delay defined in timeUnit parameter.
     * @param timeUnit TimeUnit, milliseconds, seconds, minutes, etc.
     * @return ScheduledFuture runnable
     */
    fun schedule(runnable: Runnable?, delay: Long, timeUnit: TimeUnit?): ScheduledFuture<*> {
        return this.runnable.schedule(runnable!!, delay, timeUnit!!)
    }

}
