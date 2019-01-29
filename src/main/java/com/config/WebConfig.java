package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * TODO
 *
 * @Author yue.pan3
 * @Date 2019/1/28
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private ParamDecodeResolver paramDecodeResolver;
    /**
     * 参数解析器
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(paramDecodeResolver);
    }
}
