package factory.producer;

import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducer;

public class RedisMqProducer implements ProxyProducer {

    @Override
    public void send(String message) {
        System.out.println("RedisMQ send message：" + message);
    }
}
