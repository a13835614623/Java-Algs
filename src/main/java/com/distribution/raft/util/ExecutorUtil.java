package com.distribution.raft.util;

import java.util.concurrent.*;

/**
 * @description Executors
 * @author 张子宽
 * @date 2022/02/27
 */
public class ExecutorUtil {

    private static final ExecutorService EXECUTOR_SERVICE =
            new ThreadPoolExecutor(1, 1, 1,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(30000),
                    Thread::new, new ThreadPoolExecutor.CallerRunsPolicy()
            );
    /**
     * @description 提交任务
     * @param runnable 任务
     * @return void
     * @author 张子宽
     * @date 2022/02/27
     */
    public static void submit(Runnable runnable){
        EXECUTOR_SERVICE.submit(runnable);
    }

}
