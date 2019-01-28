package com.config.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.config.decodeAni.DecodeParamEnable;
import com.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
@Component
@ControllerAdvice(basePackages = "com.login.controller")
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getMethodAnnotation(DecodeParamEnable.class) != null;
    // 如果是使用方式3 的话 这个地方一定要改为false 不然 不会走下面的代码 因为都是用于生成对象的 值会执行一个地方
//        return false;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage request, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            //方式一
            return httpInputMessage;
//            return new MyHttpInputMessage(httpInputMessage,parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //方式二 return body;
        String jsonData=new JsonMapper().toJson(body);
        try{
            return JSONObject.parseObject(jsonData, body.getClass());
        }catch (Exception e){
            return body;
        }
        //方式三  忽略这些 走过滤器
    }

    class MyHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;
        private InputStream body;
        public MyHttpInputMessage(HttpInputMessage inputMessage,MethodParameter parameter) throws Exception {
            String string = IOUtils.toString(inputMessage.getBody(), "UTF-8");
            Map<String, Object> mapJson = (Map<String, Object>) JSON.parseObject(string, Map.class);
            Map<String, Object> map = new HashMap<String, Object>();
            Set<Map.Entry<String, Object>> entrySet = mapJson.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object objValue = entry.getValue();
                if (objValue instanceof String) {
                    String value = objValue.toString();
                    map.put(key, filterDangerString(value));
                } else {
                    List<HashMap> parseArray = JSONArray.parseArray(objValue.toString(), HashMap.class);
                    List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
                    for (Map<String, Object> innerMap : parseArray) {
                        Map<String, Object> childrenMap = new HashMap<String, Object>();
                        Set<Map.Entry<String, Object>> elseEntrySet = innerMap.entrySet();
                        for (Map.Entry<String, Object> en : elseEntrySet) {
                            String innerKey = en.getKey();
                            Object innerObj = en.getValue();
                            if (innerObj instanceof String) {
                                String value = innerObj.toString();
                                childrenMap.put(innerKey, filterDangerString(value));
                            }
                        }
                        listMap.add(childrenMap);
                    }
                    map.put(key, listMap);
                }
            }
            this.headers = inputMessage.getHeaders();
            this.body = IOUtils.toInputStream(JSON.toJSONString(map), "UTF-8");
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
        private String filterDangerString(String value) {
            if (value == null) {
                return null;
            }
            return "asdasdasdasd";
        }
    }

}