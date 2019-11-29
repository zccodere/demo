package com.zccoder.demo.time.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字段名称工具类
 *
 * @author zc 2019-11-14
 */
public class FieldNameUtils {

    /**
     * 驼峰转下划线
     */
    public static String camelToSnake(String str) {
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        sb.append(str.substring(0, 1).toLowerCase());
        for (int i = 1; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String snakeToCamel(String str) {
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split("_");
        for (String charStr : arr) {
            if (!str.contains("_")) {
                sb.append(charStr);
                continue;
            }
            if (sb.length() == 0) {
                sb.append(charStr.toLowerCase());
            } else {
                sb.append(charStr.substring(0, 1).toUpperCase());
                sb.append(charStr.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
}
