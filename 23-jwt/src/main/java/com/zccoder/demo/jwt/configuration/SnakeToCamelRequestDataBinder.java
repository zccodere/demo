package com.zccoder.demo.jwt.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zccoder.demo.jwt.util.StringUtils;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;

/**
 * 下换线转驼峰参数绑定
 *
 * @author zc
 * @date 2020/10/12
 */
public class SnakeToCamelRequestDataBinder extends ExtendedServletRequestDataBinder {

    public SnakeToCamelRequestDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    @Override
    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);
        Class<?> targetClass = this.getTarget().getClass();
        List<Field> fields = new ArrayList(Arrays.asList(targetClass.getDeclaredFields()));
        Iterator var5 = ClassUtils.getAllSuperclasses(targetClass).iterator();

        while (var5.hasNext()) {
            Class<?> superclass = (Class) var5.next();
            if (!Object.class.equals(superclass)) {
                fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
            }
        }

        var5 = fields.iterator();

        while (var5.hasNext()) {
            Field field = (Field) var5.next();
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            if (jsonProperty != null && mpvs.contains(jsonProperty.value()) && !mpvs.contains(field.getName())) {
                mpvs.add(field.getName(), mpvs.getPropertyValue(jsonProperty.value()).getValue());
            }
        }

        List<PropertyValue> convertValues = new ArrayList<>(16);
        Iterator iterator = mpvs.getPropertyValueList().iterator();

        while (iterator.hasNext()) {
            PropertyValue propertyValue = (PropertyValue) iterator.next();
            if (propertyValue.getName().contains("_")) {
                String camelName = StringUtils.snake2Camel(propertyValue.getName());
                if (!mpvs.contains(camelName)) {
                    convertValues.add(new PropertyValue(camelName, propertyValue.getValue()));
                }
            }
        }

        mpvs.getPropertyValueList().addAll(convertValues);
    }
}
