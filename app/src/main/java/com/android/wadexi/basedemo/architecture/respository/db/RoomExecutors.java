package com.android.wadexi.basedemo.architecture.respository.db;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RoomExecutors {

    private ThreadPoolExecutor executor;
    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);

    @Inject
    public RoomExecutors() {
        executor = new ThreadPoolExecutor(3,5,60,TimeUnit.SECONDS,linkedBlockingQueue);

    }

    public ExecutorService getDiskIO() {
        return executor;
    }


}
