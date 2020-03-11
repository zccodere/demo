package com.zccoder.demo.unionpay.controller;

import com.zccoder.demo.unionpay.sdk.AcpService;
import com.zccoder.demo.unionpay.sdk.LogUtil;
import com.zccoder.demo.unionpay.sdk.SdkConstants;
import com.zccoder.demo.unionpay.service.Consumer;
import com.zccoder.demo.unionpay.service.ConsumerUndo;
import com.zccoder.demo.unionpay.service.bo.ConsumerReqBo;
import com.zccoder.demo.unionpay.service.bo.ConsumerUndoReqBo;
import com.zccoder.demo.unionpay.util.HtmlUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付 Controller
 *
 * @author zc 2018-09-26
 **/
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private Consumer consumer;
    @Autowired
    private ConsumerUndo consumerUndo;

    @RequestMapping("/order/result")
    public ModelAndView orderResult(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("order/pay-result");

        LogUtil.writeLog("前台接收报文返回开始");

        String encoding = request.getParameter(SdkConstants.PARAM_ENCODING);
        LogUtil.writeLog("返回报文中encoding=[" + encoding + "]");

        Map<String, String> respParam = HtmlUtils.getAllRequestParam(request);

        // 打印请求报文
        LogUtil.printRequestLog(respParam);

        Map<String, String> validData = new HashMap<>(16);
        StringBuilder page = new StringBuilder();
        if (!respParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = respParam.entrySet()
                    .iterator();
            validData = new HashMap<>(respParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = e.getKey();
                String value = e.getValue();
                value = new String(value.getBytes(encoding), encoding);
                page.append("<tr><td width=\"30%\" align=\"right\">")
                        .append(key)
                        .append("(")
                        .append(key)
                        .append(")</td><td>")
                        .append(value)
                        .append("</td></tr>");
                validData.put(key, value);
            }
        }
        if (!AcpService.validate(validData, encoding)) {
            page.append("<tr><td width=\"30%\" align=\"right\">验证签名结果</td><td>失败</td></tr>");
            LogUtil.writeLog("验证签名结果[失败].");
            modelAndView.addObject("result", page.toString());
            return modelAndView;
        }

        page.append("<tr><td width=\"30%\" align=\"right\">验证签名结果</td><td>成功</td></tr>");
        LogUtil.writeLog("验证签名结果[成功].");
        //其他字段也可用类似方式获取
        System.out.println(validData.get("orderId"));

        String respCode = validData.get("respCode");
        System.out.println(respCode);
        //判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。

        // 支付订单ID
        modelAndView.addObject("payOrderId", validData.get("orderId"));
        // 交易时间
        modelAndView.addObject("tradeTime", validData.get("txnTime"));
        // 银联支付流水号
        modelAndView.addObject("payTransId", validData.get("queryId"));
        // 交易说明
        modelAndView.addObject("message", validData.get("respMsg"));

        LogUtil.writeLog("FrontRcvResponse前台接收报文返回结束");

        return modelAndView;
    }

    @RequestMapping("/order")
    public void order(ConsumerReqBo reqBO, HttpServletResponse response) throws Exception {
        reqBO.setTxnTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        System.out.println(reqBO.toString());
        String html = this.consumer.execute(reqBO);
        //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
        response.getWriter().write(html);
    }

    @RequestMapping("/order/undo")
    public void orderUndo(ConsumerUndoReqBo reqBO, HttpServletResponse response) throws Exception {
        reqBO.setTxnTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        System.out.println(reqBO.toString());
        String result = this.consumerUndo.execute(reqBO);
        // 返回响应结果
        response.getWriter().write(result);
    }
}
