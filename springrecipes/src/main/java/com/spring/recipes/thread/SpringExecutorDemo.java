package com.spring.recipes.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

public class SpringExecutorDemo {

    @Autowired
    private SimpleAsyncTaskExecutor simpleAsyncTaskExecutor ;

    @Autowired
    private SyncTaskExecutor syncTaskExecutor ;

    @Autowired
    private TaskExecutorAdapter taskExecutorAdapter ;

}
