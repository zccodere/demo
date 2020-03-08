package com.zccoder.demo.log4j2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 演示服务
 *
 * @author zc 2018-07-06
 **/
public class DemoService extends Thread {

    private static Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Override
    public void run() {
        logger.info("DemoService info log");
        logger.debug("DemoService debug log");
        logger.warn("DemoService warn log");
        logger.error("DemoService error log");
    }
}
