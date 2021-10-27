package com.github.dynamic.threadpool.config.toolkit;

import com.github.dynamic.threadpool.common.executor.ExecutorFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Config executor.
 *
 * @author chen.ma
 * @date 2021/6/23 18:33
 */
public class ConfigExecutor {

    private static final ScheduledExecutorService LONG_POLLING_EXECUTOR = ExecutorFactory.Managed
            .newSingleScheduledExecutorService("default group", r -> new Thread(r, "long-polling"));

    public static void executeLongPolling(Runnable runnable) {
        LONG_POLLING_EXECUTOR.execute(runnable);
    }

    public static ScheduledFuture<?> scheduleLongPolling(Runnable runnable, long period, TimeUnit unit) {
        return LONG_POLLING_EXECUTOR.schedule(runnable, period, unit);
    }

    public static void scheduleLongPolling(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        LONG_POLLING_EXECUTOR.scheduleWithFixedDelay(runnable, initialDelay, period, unit);
    }

}