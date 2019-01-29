package com.config.common;

import com.config.decodeAni.DecodeParam;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auther: yuepan
 * @Description: 自定义的注解  把时间转换为时间戳 精确到毫秒
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/6/13 18:13
 * ------------------------------------------------------
 */
@Component
public class ParamsIntrospector extends JacksonAnnotationIntrospector {
    @Resource
    private ParamsSerializer paramsSerializer;
    @Resource
    private ParamsDeserializer paramsDeserializer;
    /**
     * 序列化为字符串的时候 需要调用
     * @param annotated
     * @return
     */
    @Override
    public Object findSerializer(Annotated annotated) {
        if(annotated instanceof AnnotatedMethod) {
            DecodeParam formatter = annotated.getAnnotation(DecodeParam.class);
            if(formatter != null) {
                return paramsSerializer;
            }
        }
        return super.findSerializer(annotated);
    }

    /**
     * 字符串序列化为对象的时候 需要调用
     * @param annotated
     * @return
     */
    @Override
    public Object findDeserializationConverter(Annotated annotated) {
        if(annotated instanceof AnnotatedMethod) {
            DecodeParam formatter = annotated.getAnnotation(DecodeParam.class);
            if(formatter != null) {
                return paramsDeserializer;
            }
        }
        return super.findDeserializationConverter(annotated);
    }
}
