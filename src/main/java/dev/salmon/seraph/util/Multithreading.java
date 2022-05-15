package dev.salmon.seraph.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dev.salmon.seraph.Seraph;

import java.util.Arrays;
import java.util.concurrent.*;

public class Multithreading {
    private static final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat(Seraph.ID + "-%d").build());
    private static final ScheduledExecutorService runnableExecutor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1);

    public static void runAsync(Runnable runnable) {
        executorService.submit(runnable);
    }

    public static void runAsync(Runnable... runnables) {
        Arrays.stream(runnables).forEachOrdered(Multithreading::runAsync);
    }

    public static ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        return runnableExecutor.schedule(runnable, delay, timeUnit);
    }
}
