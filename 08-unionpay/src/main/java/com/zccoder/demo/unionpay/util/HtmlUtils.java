package com.zccoder.demo.unionpay.util;

import com.zccoder.demo.unionpay.sdk.SdkConstants;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Html工具类
 *
 * @author zc 2018-09-26
 **/
public class HtmlUtils {

    /**
     * 组装请求，返回报文字符串用于显示
     */
    public static String genHtmlResult(Map<String, String> data) {
        TreeMap<String, String> tree = new TreeMap<>();
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            tree.put(en.getKey(), en.getValue());
        }
        it = tree.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            if ("respCode".equals(key)) {
                sb.append("<b>").append(key).append(SdkConstants.EQUAL).append(value).append("</br></b>");
            } else {
                sb.append(key).append(SdkConstants.EQUAL).append(value).append("</br>");
            }
        }
        return sb.toString();
    }

    /**
     * 获取请求参数中所有的信息 当商户上送frontUrl或backUrl地址中带有参数信息的时候， 这种方式会将url地址中的参数读到map中，<br/>
     * 会导多出来这些信息从而致验签失败，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<>(64);
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    // en：为空的字段名
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
