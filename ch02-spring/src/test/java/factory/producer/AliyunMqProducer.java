package factory.producer;

import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducer;

public class AliyunMqProducer implements ProxyProducer {
    @Override
    public void send(String message) {
        System.out.println("AliyunMQ send message：" + message);
    }
}
