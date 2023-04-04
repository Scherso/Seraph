package com.github.scherso.seraph.util

import com.google.common.util.concurrent.ThreadFactoryBuilder
import java.util.concurrent.*

class Multithreading {

    /**
     * Thread pool for the scheduler.
     *
     * @see ExecutorService
     */
    private val pool: ExecutorService =
        Executors.newCachedThreadPool(ThreadFactoryBuilder().setNameFormat("Aurora.ID" + "-%d").build())

    /**
     * Scheduled executor service for the scheduler.
     *
     * @see ScheduledExecutorService
     */
    private val runnable: ScheduledExecutorService =
        ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1)

    /**
     * Schedules a task to be executed after a delay.
     *
     * @param runnable runnable to be submitted and executed.
     */
    fun runAsync(runnable: Runnable?) {
        runnable?.let { pool.submit(it) }
    }

    /**
     * Schedules tasks to be executed after a delay in a foreach loop.
     *
     * @param runnables runnables to be submitted and executed.
     */
    fun runAsync(vararg runnables: Runnable?) {
        for (runnable in runnables) {
            this.runAsync(runnable)
        }
    }

    /**
     * @param runnable Runnable to be executed
     * @param delay    Delay defined in timeUnit parameter.
     * @param timeUnit TimeUnit, milliseconds, seconds, minutes, etc.
     * @return ScheduledFuture runnable
     */
    fun schedule(runnable: Runnable?, delay: Long, timeUnit: TimeUnit?): ScheduledFuture<*> {
        return this.runnable.schedule(runnable!!, delay, timeUnit)
    }
}