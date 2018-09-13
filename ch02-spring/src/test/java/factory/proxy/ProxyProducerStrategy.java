package factory.proxy;

public interface ProxyProducerStrategy {

    Class<? extends ProxyProducer> getStrategy();

}
