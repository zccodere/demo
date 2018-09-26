package com.zccoder.demo.unionpay.constant;

import com.zccoder.demo.unionpay.sdk.SdkConfig;

/**
 * 标题：常量<br>
 * 描述：常量<br>
 * 时间：2018/09/26<br>
 *
 * @author zc
 **/
public class UnionpayConstants {

    /**
     * 编码格式
     */
    public static final String ENCODING = "UTF-8";

    /**
     * 前台通知地址
     */
    public static final String FRONT_URL = SdkConfig.getConfig().getFrontUrl();
    /**
     * 后台通知地址
     */
    public static final String BACK_URL = SdkConfig.getConfig().getBackUrl();

}
