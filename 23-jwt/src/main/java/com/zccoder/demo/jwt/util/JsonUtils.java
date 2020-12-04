package com.zccoder.demo.jwt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zccoder.demo.jwt.exception.JsonSerializeException;
import com.zccoder.demo.jwt.exception.JsonToObjectException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

/**
 * JSON工具类
 *
 * @author zc
 * @date 2020/09/29
 */
@UtilityClass
public class JsonUtils {

    private static final ObjectMapper SNAKE_OBJECT_MAPPER = new ObjectMapper();

    static {
        // 设置 Jackson
        SimpleModule module = new SimpleModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        SNAKE_OBJECT_MAPPER.registerModule(module);
        SNAKE_OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        SNAKE_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 序列化为JSON字符串
     *
     * @param object 对象
     * @return JSON字符串
     */
    public static String toJsonString(Object object) {
        try {
            return SNAKE_OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new JsonSerializeException("Json序列化失败：" + ex.getMessage(), ex);
        }
    }

    /**
     * 序列化为Java对象
     *
     * @param value JSON字符串
     * @param type  java类型
     * @param <T>   java类型
     * @return 实例
     */
    public static <T> T toJavaObject(String value, Class<T> type) {
        try {
            return SNAKE_OBJECT_MAPPER.readValue(value, type);
        } catch (IOException e) {
            throw new JsonToObjectException("Json反序列化失败：" + e.getMessage(), e, value);
        }
    }

    /**
     * 序列化为Java对象，支持带泛型的参数
     *
     * @param value         JSON字符串
     * @param typeReference java类型引用
     * @param <T>           java类型
     * @return 实例
     */
    public static <T> T toJavaObject(String value, TypeReference<?> typeReference) {
        try {
            return SNAKE_OBJECT_MAPPER.readValue(value, typeReference);
        } catch (IOException ex) {
            throw new JsonToObjectException("Json反序列化失败：" + ex.getMessage(), ex, value);
        }
    }

}
