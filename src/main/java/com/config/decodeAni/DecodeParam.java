package com.config.decodeAni;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: yuepan
 * @Description: 转换时间为时间戳并精确到秒
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/6/13 18:16
 * ------------------------------------------------------
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface DecodeParam {
    DecodeParamType type();
}
