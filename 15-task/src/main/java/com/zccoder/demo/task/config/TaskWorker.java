package com.zccoder.demo.task.config;

import com.zccoder.demo.task.service.RunTaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * 任务工作类
 *
 * @author zc 2019-11-29
 */
@Component
public class TaskWorker implements CommandLineRunner, DisposableBean {

    private Logger logger = LoggerFactory.getLogger(TaskWorker.class);

    @Qualifier(TaskConfiguration.MY_TASK_EXECUTOR_BEAN_NAME)
    @Autowired
    private Executor executor;
    @Autowired
    private RunTaskService runTaskService;

    private volatile boolean continueRun = true;

    @Override
    public void run(String... args) {
        executor.execute(() -> {
            while (continueRun) {
                try {
                    runTaskService.doRun();
                } catch (Exception ex) {
                    logger.warn("执行库存同步失败：" + ex.getMessage(), ex);
                }
            }
        });
    }

    @Override
    public void destroy() {
        continueRun = false;
    }
}
