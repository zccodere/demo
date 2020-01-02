package com.demo.jfinal.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author zc 2020-01-02
 */
public final class StringUtil {

    /**
     * 使用指定分隔符拼接byte数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(byte[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接char数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(char[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接short数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(short[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接int数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(int[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接float数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(float[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接long数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(long[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接double数组
     *
     * @param arr       数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(double[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接对象数组
     *
     * @param arr       对象数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(Object[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 使用指定分隔符拼接String数组
     *
     * @param arr       对象数组
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(String[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 附带单引号的字符串拼接
     *
     * @param arr       字符串数组
     * @param separator 分隔字符
     * @return 附带单引号的字符串
     */
    public static String withSingleQuoteJoin(String[] arr, String separator) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("'").append(specialReplace(arr[i])).append("'");
            if (i < length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 把字符串用单引号包裹(适用于sql拼接values)
     *
     * @param str 源字符串
     * @return 包裹后的字符串
     */
    public static String withSingleQuote(String str) {
        return str == null ? null : "'" + specialReplace(str) + "'";
    }

    /**
     * snake字符串转换为camel字符串
     *
     * @param snake snake字符串
     * @return camel字符串
     */
    public static String snake2Camel(String snake) {
        if (snake == null || !snake.contains("_")) {
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
        } else if (isBlank(camel)) {
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

    public static String specialReplace(String s) {
        if (isNotBlank(s)) {
            s = s.replace("'", "\\'");
        }
        return s;
    }

    /**
     * 空或者空白字符串
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 非空且非空白字符串
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}