package factory.proxy;

import com.zccoder.demo.redis.mq.factorytest.DefaultProxyProducer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

public class ProxyProducerFactoryBean implements FactoryBean<ProxyProducer>, ApplicationContextAware {

    private ProxyProducerConfig config;
    private ProxyProducerWrapper wrapper;
    private ProxyProducer proxyProducer;
    private ProxyProducerStrategy localStrategy;

    @Override
    public ProxyProducer getObject() throws Exception {
        if (!config.getEnable()){
            return new DefaultProxyProducer();
        }
        if (proxyProducer != null) {
            return proxyProducer;
        }

        if (localStrategy == null){
            // 如果单个生产者未配置消息策略，则使用全局消息策略
            localStrategy = config;
        }
        wrapper = new ProxyProducerWrapper(localStrategy);

        proxyProducer = (ProxyProducer) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{ProxyProducer.class}, (proxy, method, args) -> method.invoke(wrapper, args));

        return proxyProducer;
    }

    @Override
    public Class<?> getObjectType() {
        return ProxyProducer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.config = applicationContext.getBean(ProxyProducerConfig.class);
    }

    public void setProxyProducerStrategy(ProxyProducerStrategy proxyProducerStrategy) {
        this.localStrategy = proxyProducerStrategy;
    }
}
