package com.zccoder.demo.task.service.impl;

import com.zccoder.demo.task.service.RunTaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 需要一直不停跑的业务服务实现
 *
 * @author zc 2019-11-29
 */
@Service
public class RunTaskServiceImpl implements RunTaskService {

    private Logger logger = LoggerFactory.getLogger(RunTaskServiceImpl.class);

    @Override
    public void doRun() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // 模拟实际业务场景，耗时500毫秒
        }
        logger.info("服务正在运行：" + System.currentTimeMillis());
    }
}
