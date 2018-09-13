package factory.producer;

import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducer;

public class RocketMqProducer implements ProxyProducer {
    @Override
    public void send(String message) {
        System.out.println("RocketMQ send message："+message);
    }
}
