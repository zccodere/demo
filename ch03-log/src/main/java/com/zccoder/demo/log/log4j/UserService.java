package com.zccoder.demo.log.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public void run(){
        logger.info("UserService info log");
        logger.debug("UserService debug log");
        logger.warn("UserService warn log");
        logger.error("UserService error log");
    }
}
