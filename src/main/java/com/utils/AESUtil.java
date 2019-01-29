package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES 对称加密算法
 */
public class AESUtil {
	protected static final Logger logger = LoggerFactory.getLogger(AESUtil.class);
	/**
	 *密钥key 一般使用注解 注入
	 */
	private final static String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIulr4TrtnEzySV5N6fn6FSVbTDGvCouwyfMuS4srXiMFQgRV8ETlcQu1o5ILSOkvww8Vc0n9IRABVe4k/aVRHbKOZMq6TNZKPak7RAyp5AzLImD0hzEXq0CsSTNH9U47qki7T0LhsQuku8AouGmP4USMEkpoHnAsZpZpNi71cBDAgMBAAECgYAeG8D2FaQmUBYEQFhhmTycUANUyuRy69Yj1J3weK2GGh3GuYaEGGbn/3k/AAzLbI/MYY3WR9EOWodMXH16YsB50kdiv44YrUOjvYxXNcLoAzTxNdZ4A+dxEnITFtWs3uUfWP9y71hFQUNPLgWxVAOVhmH3CEVIh2ry2LTYr8qnkQJBAM+6xXc2B4Hm/ako+fb1KMhrGVGwXqs9f5qBh5wzXnQhZV+Kh8DeydLNP5fJP+8XZEG5ivw8iRrTLQmSBxuGBz8CQQCsGORne4KZU5CIZHL05ERyvwoJDBOkY/DS/GLfYZSm+wZqTjyUHmtPshw8OqT7XuM3KbfLsfnENPzPOkDkaKn9AkBW6XIM+pIRi6+9wuMyrEgv8n9Zak1xn/1vi5pNhECUnhRn8PHzhEM066Mbwpv5UPQle5fnCPzkOE6znmFS5rTJAkEAjKbDdXfSIvHS/EXsVg2Bi65qyz4dh4tOtYEkZaDg1x/t/E/43q2F0tqkjOz5DaBdXyYvWsEYeq07uTnI82Q/GQJAOWKXAWGHutoEziR0QJ7bE2FXYq6JWMZ+KbcEWbuvJYXi2j6k9CVNFPnEMLtzKLFTEu5qtz6ru0kS7ZCxNo2PBw==";
	private final static String CONTENT_CODE_TYPE = "utf-8";
	private final static String ENCRYPT_TYPE = "AES";

	/**
	 * 解密
	 * @return
	 * @throws Exception
	 */
	public static String aesDecode(String content,String key) throws Exception {
		try {
			Cipher cipher=Cipher.getInstance(ENCRYPT_TYPE);
			cipher.init(Cipher.DECRYPT_MODE, buildSecretKey(key));
			byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
			byte [] byte_decode=cipher.doFinal(byte_content);
			return new String(byte_decode,CONTENT_CODE_TYPE);
		}catch (Exception e){
			logger.error("解密失败："+e.getMessage());
			throw e;
		}
	}

	/**
	 * 加密
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String content,String key) throws Exception {
		try {
			Cipher cipher=Cipher.getInstance(ENCRYPT_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, buildSecretKey(key));
			byte [] byte_encode=content.getBytes(CONTENT_CODE_TYPE);
			byte [] byte_AES=cipher.doFinal(byte_encode);
			return new String(new BASE64Encoder().encode(byte_AES));
		}catch (Exception e){
			logger.error("加密失败："+e.getMessage());
			throw e;
		}
	}

	/**
	 * 生成密钥对应的SecretKey
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	public static SecretKey buildSecretKey(String encryptKey) throws Exception {
		//1.构造密钥生成器，指定为AES算法,不区分大小写
		KeyGenerator keygen=KeyGenerator.getInstance(ENCRYPT_TYPE);
		//2.根据ecnodeRules规则初始化密钥生成器
		//生成一个128位的随机源,根据传入的字节数组
		keygen.init(128, new SecureRandom(encryptKey.getBytes()));
		//3.产生原始对称密钥
		SecretKey original_key=keygen.generateKey();
		//4.获得原始对称密钥的字节数组
		byte [] raw=original_key.getEncoded();
		//5.根据字节数组生成AES密钥
		return new SecretKeySpec(raw, ENCRYPT_TYPE);
	}

	public static void main(String[] args) throws Exception {
		String content = "潘月";
		String key = "asdasd";
		String encrypt = AESUtil.aesEncrypt(content,key);
		System.out.println("加密原文：" + content);
		System.out.println("加密结果：" + encrypt);
		System.out.println("解密结果：" + AESUtil.aesDecode(encrypt,key));
	}
}