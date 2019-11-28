package com.zccoder.demo.time.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 日期格式化
 *
 * @author zc 2019-08-30
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, formatter);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return formatter.format(localDate);
    }
}
