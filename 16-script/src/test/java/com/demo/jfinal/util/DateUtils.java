package com.demo.jfinal.util;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * 日期时间工具类
 *
 * @author zc 2020-01-02
 */
public class DateUtils {

    public static void main(String[] args) {
        System.out.println("预计剩余：" + getTimeFormat(1));
        System.out.println("预计剩余：" + getTimeFormat(60));
        System.out.println("预计剩余：" + getTimeFormat(659));
        System.out.println("预计剩余：" + getTimeFormat(99423));
        System.out.println("预计剩余：" + getTimeFormat(254));
    }

    public static long getSeconds(long count, long pre, long preSeconds) {
        return BigDecimal.valueOf(count).divide(BigDecimal.valueOf(pre), BigDecimal.ROUND_UP).multiply(BigDecimal.valueOf(preSeconds)).longValue();
    }

    public static String getTimeFormat(long seconds) {
        Duration duration = Duration.ofSeconds(seconds);
        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours();
        long minutes = duration.minusDays(days).minusHours(hours).toMinutes();
        long second = duration.minusDays(days).minusHours(hours).minusMinutes(minutes).getSeconds();
        StringBuilder sb = new StringBuilder(16);
        if (days > 0) {
            sb.append(days);
            sb.append("天");
        }
        if (hours > 0) {
            sb.append(hours);
            sb.append("小时");
        }
        if (minutes > 0) {
            sb.append(minutes);
            sb.append("分");
        }
        if (second > 0) {
            sb.append(second);
            sb.append("秒");
        }
        return sb.toString();
    }
}
