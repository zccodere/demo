package com.zccoder.demo.spring.factory.platform;

/**
 * 平台接口
 *
 * @author zc 2020-03-02
 */
public interface PlatformAble {

    /**
     * 获取平台编号
     *
     * @return 平台ID
     */
    int getPlatformId();

    /**
     * 基于平台编号获取平台名称
     *
     * @param platformId 平台编号
     * @return 平台名称
     */
    String getPlatformName(Integer platformId);

    /**
     * 执行其他方法
     *
     * @param platformId 平台编号
     * @return 结果
     */
    String doOtherMethod(Integer platformId);

}