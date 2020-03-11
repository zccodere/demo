package com.zccoder.demo.spring.schema.support;

/**
 * 定义服务配置项
 *
 * @author zc 2018-10-16
 **/
public class ServiceConfig {

    /**
     * 服务ID
     */
    private String id;
    /**
     * 服务接口全类名
     */
    private String serviceInterface;
    /**
     * 服务实例
     */
    private String ref;

    @Override
    public String toString() {
        return "ServiceConfig{" +
                "id='" + id + '\'' +
                ", serviceInterface='" + serviceInterface + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }

    /**
     * 获取 服务ID
     *
     * @return id 服务ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置 服务ID
     *
     * @param id 服务ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 服务接口全类名
     *
     * @return serviceInterface 服务接口全类名
     */
    public String getServiceInterface() {
        return this.serviceInterface;
    }

    /**
     * 设置 服务接口全类名
     *
     * @param serviceInterface 服务接口全类名
     */
    public void setInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    /**
     * 获取 服务实例
     *
     * @return ref 服务实例
     */
    public String getRef() {
        return this.ref;
    }

    /**
     * 设置 服务实例
     *
     * @param ref 服务实例
     */
    public void setRef(String ref) {
        this.ref = ref;
    }
}
