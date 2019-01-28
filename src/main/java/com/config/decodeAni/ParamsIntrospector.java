package com.config.decodeAni;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * @Auther: yuepan
 * @Description: 自定义的注解  把时间转换为时间戳 精确到毫秒
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/6/13 18:13
 * ------------------------------------------------------
 */
public class ParamsIntrospector extends JacksonAnnotationIntrospector {
    /**
     * 序列化为字符串的时候 需要调用
     * @param annotated
     * @return
     */
    @Override
    public Object findSerializer(Annotated annotated) {
        if(annotated instanceof AnnotatedMethod) {
            DecodeParams formatter = annotated.getAnnotation(DecodeParams.class);
            if(formatter != null) {
                return new ParamsSeri();
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
            DecodeParams formatter = annotated.getAnnotation(DecodeParams.class);
            if(formatter != null) {
                return new ParamsDeserializer();
            }
        }
        return super.findDeserializationConverter(annotated);
    }
}