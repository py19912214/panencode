package com.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 非对称加密算法
 *
 * @Author yue.pan3
 * @Date 2019/1/29
 **/
public class RSAUtil {

    private final static String ENCRYPT_TYPE = "RSA";
    private final static String CONTENT_CODE_TYPE = "utf-8";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK  = 128;
    public static String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLpa+E67ZxM8kleTen5+hUlW0wxrwqLsMnzLkuLK14jBUIEVfBE5XELtaOSC0jpL8MPFXNJ/SEQAVXuJP2lUR2yjmTKukzWSj2pO0QMqeQMyyJg9IcxF6tArEkzR/VOO6pIu09C4bELpLvAKLhpj+FEjBJKaB5wLGaWaTYu9XAQwIDAQAB";
    public static String privateKeyString="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIulr4TrtnEzySV5N6fn6FSVbTDGvCouwyfMuS4srXiMFQgRV8ETlcQu1o5ILSOkvww8Vc0n9IRABVe4k/aVRHbKOZMq6TNZKPak7RAyp5AzLImD0hzEXq0CsSTNH9U47qki7T0LhsQuku8AouGmP4USMEkpoHnAsZpZpNi71cBDAgMBAAECgYAeG8D2FaQmUBYEQFhhmTycUANUyuRy69Yj1J3weK2GGh3GuYaEGGbn/3k/AAzLbI/MYY3WR9EOWodMXH16YsB50kdiv44YrUOjvYxXNcLoAzTxNdZ4A+dxEnITFtWs3uUfWP9y71hFQUNPLgWxVAOVhmH3CEVIh2ry2LTYr8qnkQJBAM+6xXc2B4Hm/ako+fb1KMhrGVGwXqs9f5qBh5wzXnQhZV+Kh8DeydLNP5fJP+8XZEG5ivw8iRrTLQmSBxuGBz8CQQCsGORne4KZU5CIZHL05ERyvwoJDBOkY/DS/GLfYZSm+wZqTjyUHmtPshw8OqT7XuM3KbfLsfnENPzPOkDkaKn9AkBW6XIM+pIRi6+9wuMyrEgv8n9Zak1xn/1vi5pNhECUnhRn8PHzhEM066Mbwpv5UPQle5fnCPzkOE6znmFS5rTJAkEAjKbDdXfSIvHS/EXsVg2Bi65qyz4dh4tOtYEkZaDg1x/t/E/43q2F0tqkjOz5DaBdXyYvWsEYeq07uTnI82Q/GQJAOWKXAWGHutoEziR0QJ7bE2FXYq6JWMZ+KbcEWbuvJYXi2j6k9CVNFPnEMLtzKLFTEu5qtz6ru0kS7ZCxNo2PBw==";

    //将base64编码后的公钥字符串转成PublicKey实例
    public static PublicKey getPublicKey(String publicKey) throws Exception{
        byte[ ] keyBytes= Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance(ENCRYPT_TYPE);
        return keyFactory.generatePublic(keySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[ ] keyBytes=Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance(ENCRYPT_TYPE);
        return keyFactory.generatePrivate(keySpec);
    }

    //公钥加密 公钥
    public static String encrypt(String content, String key) throws Exception{
        return encryptBytes(content.getBytes(CONTENT_CODE_TYPE), getPublicKey(key));
    }

    public static String encryptBytes(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(readData(cipher,content,MAX_ENCRYPT_BLOCK));
    }

    //私钥解密 用私钥
    public static String decrypt(String content, String key) throws Exception{
        return decryptBytes(Base64.getDecoder().decode(content), getPrivateKey(key));
    }
    public static String decryptBytes(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(readData(cipher,content,MAX_DECRYPT_BLOCK),CONTENT_CODE_TYPE);
    }

    /**
     * 分段 加密 解密数据
     * @param cipher
     * @param content
     * @param maxLength
     * @return
     * @throws Exception
     */
    public static byte[] readData(Cipher cipher,byte[] content,int maxLength) throws Exception {
        int inputLen = content.length;
        ByteArrayOutputStream out = null;
        int offSet = 0;
        byte[] cache;
        int i = 0;
        try {
            out = new ByteArrayOutputStream();
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxLength) {
                    cache = cipher.doFinal(content, offSet, maxLength);
                } else {
                    cache = cipher.doFinal(content, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxLength;
            }
            return out.toByteArray();
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        //公钥加密
        String content=encrypt("阿斯",publicKeyString);
        System.out.println("加密后："+content);

        //私钥解密
        System.out.println("解密后："+decrypt(content,privateKeyString));

    }
}
