package com.config.common;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.utils.RSAUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
public class ParamsDeserializer extends StdConverter {
    @Value("${RSA.private_key}")
    private String privateKey;
    @Override
    public Object convert(Object o) {
        try {
            String newData= RSAUtil.decrypt(String.valueOf(o),privateKey);
            return newData;
        } catch (Exception e) {
            log.error("解析报错 然后写入空字符串 错误信息："+e.getMessage());
        }
        return StringUtils.EMPTY;
    }
}
