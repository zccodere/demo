package com.zccoder.demo.log4j2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 标题：用户服务<br>
 * 描述：用户服务<br>
 * 时间：2018/07/06<br>
 *
 * @author zc
 **/
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public void run() {
        logger.info("UserService info log");
        logger.debug("UserService debug log");
        logger.warn("UserService warn log");
        logger.error("UserService error log");
    }
}
