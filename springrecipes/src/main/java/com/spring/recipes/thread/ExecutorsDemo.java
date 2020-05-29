package com.spring.recipes.thread;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.concurrent.*;

public class ExecutorsDemo {
    public static void main(String[] args) throws Throwable {
        Runnable task = new DemonstrationRunnable();
        ExecutorService cacheThreadPoolExecutorService = Executors.newCachedThreadPool();
        if( cacheThreadPoolExecutorService.submit(task).get() == null ){
            System.out.printf("the cachedThreadPoolExecutorService has successed at %s \n ", new Date());
        }

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        if( fixedThreadPool.submit(task).get() == null ){
            System.out.printf("the fixedThreadPool has successed at %s \n ", new Date());
        }

        ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
        if( singleThreadExecutorService.submit(task).get() == null ){
            System.out.printf("The singleThreadExecutorService has successed at %s \n", new Date());
        }

        ExecutorService es = Executors.newCachedThreadPool();
        if( es.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE)){
            System.out.printf("Job has finished ");
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        if( scheduledExecutorService.schedule(task, 30, TimeUnit.SECONDS).get() == null ){
            System.out.printf("the scheduledThreadExecutorService has successed at %s \n", new Date());
        }

        scheduledExecutorService.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);
    }
}
