package com.zccoder.demo.unionpay.service;

import com.zccoder.demo.unionpay.constant.UnionpayConstants;
import com.zccoder.demo.unionpay.sdk.AcpService;
import com.zccoder.demo.unionpay.sdk.LogUtil;
import com.zccoder.demo.unionpay.sdk.SdkConfig;
import com.zccoder.demo.unionpay.service.bo.ConsumerReqBo;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 标题：消费服务（支付订单）
 *
 * @author zc 2018-09-26
 **/
@Service
public class Consumer {

    public String execute(ConsumerReqBo reqBO) {

        SdkConfig.getConfig().loadPropertiesFromSrc();

        Map<String, String> requestData = new HashMap<>(64);

        // 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改
        // 版本号，全渠道默认值
        requestData.put("version", SdkConfig.getConfig().getVersion());
        // 字符集编码，可以使用UTF-8,GBK两种方式
        requestData.put("encoding", UnionpayConstants.ENCODING);
        requestData.put("signMethod", SdkConfig.getConfig().getSignMethod());
        // 交易类型 ，01：消费
        requestData.put("txnType", "01");
        // 交易子类型， 01：自助消费
        requestData.put("txnSubType", "01");
        // 业务类型，B2C网关支付，手机wap支付
        requestData.put("bizType", "000201");
        // 渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
        requestData.put("channelType", "07");


        // 商户接入参数
        //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
        requestData.put("merId", reqBO.getMerId());
        //接入类型，0：直连商户
        requestData.put("accessType", "0");
        //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("orderId", reqBO.getOrderId());
        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("txnTime", reqBO.getTxnTime());
        //交易币种（境内商户一般是156 人民币）
        requestData.put("currencyCode", "156");
        //交易金额，单位分，不要带小数点
        requestData.put("txnAmt", reqBO.getTxnAmt());
        // 透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节。
        // 出现&={}[]符号时可能导致查询接口应答报文解析失败，建议尽量只传字母数字并使用|分割，或者可以最外层做一次base64编码(base64编码之后出现的等号不会导致解析失败可以不用管)。
        requestData.put("reqReserved", "MYFILED");

        requestData.put("riskRateInfo", "{commodityName=GOODSNAME}");

        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
        //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
        //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        requestData.put("frontUrl", UnionpayConstants.FRONT_URL);

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        requestData.put("backUrl", UnionpayConstants.BACK_URL);

        // 订单超时时间。
        // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
        // 此时间建议取支付时的北京时间加15分钟。
        // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
        requestData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis() + 15 * 60 * 1000));

        // 请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面
        //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        Map<String, String> submitFromData = AcpService.sign(requestData, UnionpayConstants.ENCODING);
        //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String requestFrontUrl = SdkConfig.getConfig().getFrontRequestUrl();
        //生成自动跳转的Html表单
        String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData, UnionpayConstants.ENCODING);

        LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
        return html;
    }

}
