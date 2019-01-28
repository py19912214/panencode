package com.config.decodeAni;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @Auther: yuepan
 * @Description:
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/6/13 18:16
 * ------------------------------------------------------
 */
public class ParamsDeserializer extends StdConverter {

    @Override
    public Object convert(Object o) {
        System.out.println("老值："+String.valueOf(o));
        return "new_"+o;
    }
}
