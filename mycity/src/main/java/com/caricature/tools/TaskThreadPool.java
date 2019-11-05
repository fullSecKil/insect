package com.caricature.tools;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @author dusk
 * @since 2019/11/6 0:49
 */
public class TaskThreadPool {

    private static final int DEFAULT_MAX_CONCURRENT = Runtime.getRuntime().availableProcessors() + 30;

    private static final long DEFAULT_KEEP_ALIVE = 90L;

    private static final int DEFAULT_SIZE = 60;

    private static final String THREAD_POOL_NAME = "ExternalConvertProcessPool-%d";

    private static final ThreadFactory FACTORY = new BasicThreadFactory.Builder().namingPattern(THREAD_POOL_NAME)
            .daemon(true).build();

    private static ExecutorService executor;

    static {
        executor = new ThreadPoolExecutor(3, DEFAULT_MAX_CONCURRENT, DEFAULT_KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(DEFAULT_SIZE), FACTORY);
    }

    public static boolean executeTask(Runnable task) {

        try {
            executor.execute(task);
        } catch (RejectedExecutionException e) {
            return false;
        }
        return true;
    }

    public static <T> Future<T> submitTask(Callable<T> task) {

        try {
            return executor.submit(task);
        } catch (RejectedExecutionException e) {
            throw new UnsupportedOperationException("Unable to submit the task, rejected.", e);
        }
    }
}
