package com.zccoder.demo.jwt.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author zc
 * @date 2020/10/12
 */
public class StringUtils {

    /**
     * snake字符串转换为camel字符串
     *
     * @param snake snake字符串
     * @return camel字符串
     */
    public static String snake2Camel(String snake) {
        final String underLine = "_";
        if (snake == null || !snake.contains(underLine)) {
            return snake;
        }
        StringBuilder camel = new StringBuilder();
        String[] split = snake.split("_");
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                camel.append(split[i].toLowerCase());
            } else {
                camel.append(upperCaseFirst(split[i]));
            }
        }
        return camel.toString();
    }

    /**
     * camel字符串转换为snake字符串
     *
     * @param camel camel字符串
     * @return snake字符串
     */
    public static String camel2Snake(String camel) {
        if (camel == null) {
            return null;
        } else if (org.apache.commons.lang3.StringUtils.isBlank(camel)) {
            return "";
        } else {
            // 驼峰字符数组
            char[] camelChars = camel.toCharArray();
            List<Character> snakeCharList = new ArrayList<>(camelChars.length);
            for (int i = 0; i < camelChars.length; i++) {
                char camelChar = camelChars[i];
                // 大写
                if (Character.isUpperCase(camelChar)) {
                    char lowerChar = Character.toLowerCase(camelChar);
                    if (i == 0) {
                        snakeCharList.add(lowerChar);
                    }
                    // _x
                    else {
                        snakeCharList.add('_');
                        snakeCharList.add(lowerChar);
                    }
                } else {
                    snakeCharList.add(camelChar);
                }
            }
            char[] snakeChars = new char[snakeCharList.size()];
            for (int i = 0; i < snakeChars.length; i++) {
                snakeChars[i] = snakeCharList.get(i);
            }
            return new String(snakeChars);
        }
    }

    /**
     * 字符串首字符大写
     *
     * @param s 源字符串
     * @return 大写后字符串
     */
    public static String upperCaseFirst(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        if (s.length() == 1) {
            return s.toUpperCase();
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
