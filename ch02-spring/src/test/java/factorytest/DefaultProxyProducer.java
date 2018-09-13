package factorytest;

import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducer;

public class DefaultProxyProducer implements ProxyProducer {
    @Override
    public void send(String message) {
        System.out.println("插件未启用，不发送消息");
    }
}
