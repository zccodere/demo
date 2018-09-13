package factory.proxy;

public class ProxyProducerWrapper implements ProxyProducer {

    private ProxyProducer proxyProducer;

    public ProxyProducerWrapper(ProxyProducerStrategy strategy){
        try {
            proxyProducer = strategy.getStrategy().newInstance();
        } catch (Exception e) {
            System.err.println("创建生产者实例异常:"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void send(String message) {
        proxyProducer.send(message);
    }
}
