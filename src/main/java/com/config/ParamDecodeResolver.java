package com.config;

/**
 * TODO
 *
 * @Author yue.pan3
 * @Date 2019/1/25
 **/

import com.config.decodeAni.DecodeParamEnable;
import com.utils.JsonMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class ParamDecodeResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(DecodeParamEnable.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while((rd = reader.read(buf)) != -1){
            sb.append(buf, 0, rd);
        }
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.fromJson(sb.toString(),parameter.getParameterType());
    }

}