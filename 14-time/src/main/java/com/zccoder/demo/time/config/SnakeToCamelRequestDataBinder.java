package com.zccoder.demo.time.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zccoder.demo.time.util.FieldNameUtils;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

/**
 * 请求参数下划线转驼峰
 *
 * @author zc 2019-11-29
 */
public class SnakeToCamelRequestDataBinder extends ExtendedServletRequestDataBinder {

    public SnakeToCamelRequestDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    @Override
    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);

        Class<?> targetClass = getTarget().getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            if (jsonProperty != null && mpvs.contains(jsonProperty.value())) {
                if (!mpvs.contains(field.getName())) {
                    mpvs.add(field.getName(), mpvs.getPropertyValue(jsonProperty.value()).getValue());
                }
            }
        }
        List<PropertyValue> convertValues = new ArrayList<>();
        for (PropertyValue propertyValue : mpvs.getPropertyValueList()) {
            if (propertyValue.getName().contains("_")) {
                String camelName = FieldNameUtils.snakeToCamel(propertyValue.getName());
                if (!mpvs.contains(camelName)) {
                    convertValues.add(new PropertyValue(camelName, propertyValue.getValue()));
                }
            }
        }
        mpvs.getPropertyValueList().addAll(convertValues);
    }
}
