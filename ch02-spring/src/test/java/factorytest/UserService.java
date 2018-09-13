package factorytest;

import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ProxyProducer producer;

    public void create(String name){
        System.out.println("创建用户："+name);
        producer.send(name);
    }

    public void delete(String name){
        System.out.println("删除用户："+name);
        producer.send(name);
    }

    public void query(String name){
        System.out.println("查询用户："+name);
        producer.send(name);
    }

}
