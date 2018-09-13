package factory.proxy;

public class ProxyProducerConfig implements ProxyProducerStrategy{

    private Boolean enable;

    private Class<? extends ProxyProducer> strategy;

    public Class<? extends ProxyProducer> getStrategy() {
        return strategy;
    }

    public void setStrategy(Class<? extends ProxyProducer> strategy) {
        this.strategy = strategy;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
