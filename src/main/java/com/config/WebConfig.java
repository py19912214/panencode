package com.config;

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
    /**
     * 参数解析器
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(paramDecodeResolver());
    }

    /**
     * 参数反序列化的时候 需要做处理
     * @return
     */
    private ParamDecodeResolver paramDecodeResolver() {
        return new ParamDecodeResolver();
    }
}
