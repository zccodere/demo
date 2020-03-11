package com.zccoder.demo.unionpay;

import com.alibaba.fastjson.JSON;
import com.zccoder.demo.unionpay.constant.UnionpayConstants;
import com.zccoder.demo.unionpay.sdk.AcpService;
import com.zccoder.demo.unionpay.sdk.SdkConfig;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易状态查询接口测试
 *
 * @author zc 2018-09-26
 **/
public class QueryTest {

    @Before
    public void init() {
        SdkConfig.getConfig().loadPropertiesFromSrc();
    }

    /**
     * 查询 消费接口返回的查询流水号
     */
    @Test
    public void queryConsumerTest() {
        // 商户 支付订单号
        String orderId = "paytest009";
        // 商户 支付订单发送时间
        String txnTime = "20180927144954";

        Map<String, String> rspData = this.query(orderId, txnTime);

        System.out.println(JSON.toJSONString(rspData));

        // {"bizType":"000201","orderId":"paytest009","signature":"sign","traceNo":"749501","settleAmt":"1",
        // "settleCurrencyCode":"156","settleDate":"0927","txnType":"01","queryId":"701809271449547495018",
        // "accessType":"0","issuerIdentifyMode":"0","reqReserved":"MYFILED","traceTime":"0927144954",
        // "txnTime":"20180927144954","signPubKeyCert":"cert","txnSubType":"01","accNo":"6216***********0018",
        // "encoding":"UTF-8","version":"5.1.0","origRespMsg":"成功[0000000]","respMsg":"成功[0000000]",
        // "merId":"777290058163386","currencyCode":"156","origRespCode":"00","respCode":"00",
        // "signMethod":"01","txnAmt":"1"}
    }

    /**
     * 查询 消费撤销接口返回的查询流水号
     */
    @Test
    public void queryConsumerUndoTest() {
        // 商户 退款订单号
        String orderId = "paytestref004";
        // 商户 退款订单发送时间
        String txnTime = "20180927145106";

        Map<String, String> rspData = this.query(orderId, txnTime);

        System.out.println(JSON.toJSONString(rspData));

        // {"bizType":"000201","orderId":"paytestref004","signature":"sign","traceNo":"749876","settleAmt":"1",
        // "settleCurrencyCode":"156","settleDate":"0927","txnType":"31","queryId":"701809271451067498768",
        // "accessType":"0","reqReserved":"MYFILED","traceTime":"0927145106","txnTime":"20180927145106",
        // "signPubKeyCert":"cert","txnSubType":"00","accNo":"6216***********0018","encoding":"UTF-8",
        // "version":"5.1.0","origRespMsg":"成功[0000000]","respMsg":"成功[0000000]","merId":"777290058163386",
        // "currencyCode":"156","origRespCode":"00","respCode":"00","signMethod":"01","txnAmt":"1"}
    }

    /**
     * 交易状态查询接口
     *
     * @param orderId 商户订单号
     * @param txnTime 商户订单发送时间（格式：YYYYMMDDhhmmss）
     * @return 查询结果
     */
    private Map<String, String> query(String orderId, String txnTime) {
        String txnType = "00";
        String txnSubType = "00";
        String merId = "777290058163386";

        Map<String, String> data = new HashMap<>(64);
        data.put("version", SdkConfig.getConfig().getVersion());
        data.put("encoding", UnionpayConstants.ENCODING);
        data.put("signMethod", SdkConfig.getConfig().getSignMethod());
        // 交易类型 00：默认
        data.put("txnType", txnType);
        //交易子类型  默认00
        data.put("txnSubType", txnSubType);
        //业务类型 B2C网关支付，手机wap支付
        data.put("bizType", "000201");

        data.put("merId", merId);
        //接入类型，商户接入固定填0，不需修改
        data.put("accessType", "0");

        data.put("orderId", orderId);
        data.put("txnTime", txnTime);

        // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        Map<String, String> reqData = AcpService.sign(data, UnionpayConstants.ENCODING);

        String url = SdkConfig.getConfig().getSingleQueryUrl();
        //这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
        return AcpService.post(reqData, url, UnionpayConstants.ENCODING);
    }
}
