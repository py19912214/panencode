package com.config.decodeAni;

/**
 * 提供的加密类型 但是现在 没有做 都是统一一个解密方式
 *
 * @Author yue.pan3
 * @Date 2019/1/25
 **/
public enum DecodeParamType {
    DEFAULT,
    ID_CARD,
    PHONE,
    BANK_CARD,
    NAME,
    ID_CARD_PIC;
    private DecodeParamType() {
    }
}