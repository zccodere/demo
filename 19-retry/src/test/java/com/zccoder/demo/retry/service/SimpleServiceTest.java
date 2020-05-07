package com.zccoder.demo.retry.service;

import com.zccoder.demo.retry.RetryApplicationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleServiceTest extends RetryApplicationTest {

    @Autowired
    private SimpleService simpleService;

    @Test
    public void service() {
        String sss = simpleService.service("sss");
        System.out.println(sss);
    }

}