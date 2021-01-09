package com.yuansb.demo.taskExecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行
 *
 * 结果是并发执行而不是顺序执行
 */
public class TestMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

        AsyncTaskService asyncTaskService
                = context.getBean(AsyncTaskService.class);

        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }

        context.close();

    }

}
