package com.zccoder.demo.retry.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

/**
 * 自定义重试监听器
 *
 * @author zc
 * @date 2020/05/07
 */
public class MyRetryListener implements RetryListener {

    private static final Logger log = LoggerFactory.getLogger(MyRetryListener.class);

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        // 在执行被 @Retryable 修饰的方法前，执行
        log.info("open");
        // 返回true，则正常执行；返回false，则拦截方法直接抛出 TerminatedRetryException
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // 被 @Retryable 修饰的方法执行完毕后，执行
        log.info("close");
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // @Retryable 修饰的方法 抛出异常时，执行
        log.info("onError");
    }
}
