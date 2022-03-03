package dev.salmon.seraph.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.salmon.seraph.Seraph;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.*;

public class Handler {

    public static final Locale LOCALE = getLocale();
    private static final DecimalFormat decimalFormat = new DecimalFormat("##.##");
    private static final GsonBuilder builder = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
    private static final ScheduledExecutorService runnableExecutor = new ScheduledThreadPoolExecutor(8);
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors() + 1;

    private static Locale getLocale() {
        return new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
    }

    public static void asExecutor(Runnable... runnables) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS, new ThreadFactoryBuilder().setNameFormat(Seraph.ID + "h-%d").build());
        Arrays.stream(runnables).forEachOrdered(executorService::submit);
    }

    public static Gson getGson() {
        return builder.setDateFormat(getDate().toPattern()).create();
    }

    public static SimpleDateFormat getDate() {
        return new SimpleDateFormat("EEEEE dd MMMMM yyyy", LOCALE);
    }

    public static String plsSplit(double o) {
        return decimalFormat.format(o);
    }

    public static ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        return runnableExecutor.schedule(runnable, delay, timeUnit);
    }

}
