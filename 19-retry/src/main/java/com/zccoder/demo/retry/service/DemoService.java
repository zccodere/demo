package com.zccoder.demo.retry.service;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * 示例服务
 *
 * @author zc
 * @date 2020/05/07
 */
@Service
public class DemoService {
//
//    @Retryable(recover = "service1Recover", value = RemoteAccessException.class)
//    public void service1(String str1, String str2) {
//        // ... do something
//    }
//
//    @Retryable(recover = "service2Recover", value = RemoteAccessException.class)
//    public void service2(String str1, String str2) {
//        // ... do something
//    }
//
//    @Recover
//    public void service1Recover(RemoteAccessException e, String str1, String str2) {
//        // ... error handling making use of original args if required
//    }
//
//    @Recover
//    public void service2Recover(RemoteAccessException e, String str1, String str2) {
//        // ... error handling making use of original args if required
//    }

}
