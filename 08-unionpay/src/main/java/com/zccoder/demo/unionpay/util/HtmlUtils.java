package com.zccoder.demo.unionpay.util;

import com.zccoder.demo.unionpay.sdk.SdkConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 标题：Html工具类<br>
 * 描述：Html工具类<br>
 * 时间：2018/09/26<br>
 *
 * @author zc
 **/
public class HtmlUtils {

    /**
     * 组装请求，返回报文字符串用于显示
     *
     * @param data
     * @return
     */
    public static String genHtmlResult(Map<String, String> data) {

        TreeMap<String, String> tree = new TreeMap<String, String>();
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            tree.put(en.getKey(), en.getValue());
        }
        it = tree.entrySet().iterator();
        StringBuffer sf = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            if ("respCode".equals(key)) {
                sf.append("<b>" + key + SdkConstants.EQUAL + value + "</br></b>");
            } else {
                sf.append(key + SdkConstants.EQUAL + value + "</br>");
            }
        }
        return sf.toString();
    }

    /**
     * 获取请求参数中所有的信息
     * 当商户上送frontUrl或backUrl地址中带有参数信息的时候，
     * 这种方式会将url地址中的参数读到map中，会导多出来这些信息从而致验签失败，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
     *
     * @param request
     * @return
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
