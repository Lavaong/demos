package com.ltlon.restapi.io;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

public class TimeServerHandlerExecutePool {

    private ExecutorService executor = null;

    public TimeServerHandlerExecutePool(int queueSize, int maximumPoolSize) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maximumPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
}
