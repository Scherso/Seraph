package dev.salmon.seraph.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class Multithreading {

    private static final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("TabStats-%d").build());
    private static final ScheduledExecutorService runnableExecutor = new ScheduledThreadPoolExecutor(8);

    public static void runAsync(Runnable runnable) {
        executorService.submit(runnable);
    }

    public static ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        return runnableExecutor.schedule(runnable, delay, timeUnit);
    }

}
