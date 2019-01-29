package com.config.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.utils.RSAUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Auther: yuepan
 * @Description:
 * <p>
 * ------------------------------------------------------
 * @Date: 2018/6/13 18:16
 * ------------------------------------------------------
 */
@Component
@Slf4j
@Data
public class ParamsSerializer extends JsonSerializer<String> {
    @Value("${RSA.private_key}")
    private String privateKey;

    @Override
    public void serialize(String value, JsonGenerator jgen,
                          SerializerProvider provider)
            throws IOException {
        try {
            jgen.writeString(RSAUtil.decrypt(value,privateKey));
        } catch (Exception e) {
            log.error("解析报错 然后写入空字符串 错误信息："+e.getMessage());
            jgen.writeString(StringUtils.EMPTY);
        }
    }
}
