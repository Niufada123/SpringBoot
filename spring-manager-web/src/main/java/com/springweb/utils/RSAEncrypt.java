package com.springweb.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {


    public static void main(String[] args) throws Exception {

        testAll();

    }

    public static void test() throws Exception {
        //生成公钥和私钥
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmqgoIV7+9E2JI7FHFPyEiFzaCufvP0JNIqR3HoytzME9Nt1c3ykMClOpgO8drsZILfAx/rXLdsWC4jjKORWSFDJ+UEqD6y8qOyzFwK3sRR9zdS7rWxdl62IfsdQdHptlygQ/MC09a8koXjbUdiqadf0NkjMiMaZfnHoqWbWmttQIDAQAB");
        keyMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKaqCghXv70TYkjsUcU/ISIXNoK5+8/Qk0ipHcejK3MwT023VzfKQwKU6mA7x2uxkgt8DH+tct2xYLiOMo5FZIUMn5QSoPrLyo7LMXArexFH3N1LutbF2XrYh+x1B0em2XKBD8wLT1rySheNtR2Kpp1/Q2SMyIxpl+ceipZtaa21AgMBAAECgYBuxJJ4awGXQ5vOBapvIv79blofVkbDHsfUwfl15r+JBjGe4FyKStZwj9KZ9QEcVV9QXLjd3sR6DVrQLknxfrNIGtKnw15tKrNvec+nt/TqKXlY1sQThouBGmISLl2kTe0mIdOdVfJ5bGpe8miH73/3C6eGMnCWfcT3/acLYP++vQJBAOBgIggoFqZlQi0IOQhC7tM8VeMG/D8ie8gJK2O86Q72elz/WLTs4ttIQZLDxEu3buFs5PUTpDnP9cuXRu3EF/cCQQC+J5R4ZrQZEr6yIyoal+3NzpyIE42KfOgYrOZc2E9BhaX2vVT+xyPhOARfG5TOiU0BNXiy3H9MGG24cj/e13SzAkBzEnqBqmWrYuUkiUIOrZ0kcp4tt+hoTLwk5Cb/mOQCC4DH7yFEcPULtywCJCqpFmNkc1+dHTytda0+g9AZoucTAkB59IiUb8oyCoOjXEo0pBwwUsKxw1iT6Wgx6zITeefa7gxzIxrQDIhGedbT6KyXiheJHvI6RJCgDUrRcPTlxulhAkEAjHzGzkMmzFM02WqUidyv3TQzCBaoSBeWr+69j6PeLg/i+3szERleWXiHtgPb/s+DzY0P+rZYkILHuxNRiKQU2A==");
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);
        // 原始字符串
        String message = "我是测试 原始内容";
        System.out.println("原始数据："+message);
        long startTime = System.currentTimeMillis();
        for (int i=0;i<10;i++){
            String messageEn = publicKeyEncrypt(message, publicKey);
            System.out.println("公钥加密后内容:" + messageEn);
            String messageDe = privateKeyDecrypt(messageEn, privateKey);
            System.out.println("私钥解密后内容:" + messageDe);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }

    /**
     * 公加私解
     * 私加公解
     * @throws Exception
     */
    public static void testAll() throws Exception {
        //生成公钥和私钥
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmqgoIV7+9E2JI7FHFPyEiFzaCufvP0JNIqR3HoytzME9Nt1c3ykMClOpgO8drsZILfAx/rXLdsWC4jjKORWSFDJ+UEqD6y8qOyzFwK3sRR9zdS7rWxdl62IfsdQdHptlygQ/MC09a8koXjbUdiqadf0NkjMiMaZfnHoqWbWmttQIDAQAB");
        keyMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKaqCghXv70TYkjsUcU/ISIXNoK5+8/Qk0ipHcejK3MwT023VzfKQwKU6mA7x2uxkgt8DH+tct2xYLiOMo5FZIUMn5QSoPrLyo7LMXArexFH3N1LutbF2XrYh+x1B0em2XKBD8wLT1rySheNtR2Kpp1/Q2SMyIxpl+ceipZtaa21AgMBAAECgYBuxJJ4awGXQ5vOBapvIv79blofVkbDHsfUwfl15r+JBjGe4FyKStZwj9KZ9QEcVV9QXLjd3sR6DVrQLknxfrNIGtKnw15tKrNvec+nt/TqKXlY1sQThouBGmISLl2kTe0mIdOdVfJ5bGpe8miH73/3C6eGMnCWfcT3/acLYP++vQJBAOBgIggoFqZlQi0IOQhC7tM8VeMG/D8ie8gJK2O86Q72elz/WLTs4ttIQZLDxEu3buFs5PUTpDnP9cuXRu3EF/cCQQC+J5R4ZrQZEr6yIyoal+3NzpyIE42KfOgYrOZc2E9BhaX2vVT+xyPhOARfG5TOiU0BNXiy3H9MGG24cj/e13SzAkBzEnqBqmWrYuUkiUIOrZ0kcp4tt+hoTLwk5Cb/mOQCC4DH7yFEcPULtywCJCqpFmNkc1+dHTytda0+g9AZoucTAkB59IiUb8oyCoOjXEo0pBwwUsKxw1iT6Wgx6zITeefa7gxzIxrQDIhGedbT6KyXiheJHvI6RJCgDUrRcPTlxulhAkEAjHzGzkMmzFM02WqUidyv3TQzCBaoSBeWr+69j6PeLg/i+3szERleWXiHtgPb/s+DzY0P+rZYkILHuxNRiKQU2A==");
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);
        // 原始字符串
        String message = "我是测试原始内容";
        System.out.println("原始数据："+message);
        String messageEn = publicKeyEncrypt(message, publicKey);
        System.out.println("公钥加密后内容:" + messageEn);
        String messageDe = privateKeyDecrypt(messageEn, privateKey);
        System.out.println("私钥解密后内容:" + messageDe);

        System.out.println("=====================");

        //私钥加密，公钥解密
        String s = privateKeyEncrypt(message, privateKey);
        System.out.println("私钥加密后内容："+s);
        String s1 = publicKeyDecrypt(s, publicKey);
        System.out.println("公钥解密后内容："+s1);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Map<String, String> map = new HashMap<>();
        map.put("publicKey", publicKeyString);
        map.put("privateKey", privateKeyString);
        return map;
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String publicKeyEncrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").
                generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String privateKeyDecrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    /**
     * RSA私钥加密
     *
     * @param str
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String privateKeyEncrypt(String str, String privateKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        PrivateKey priKey = KeyFactory.getInstance("RSA").
                generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes()));
        return outStr;
    }

    /**
     * RSA公钥解密
     *
     * @param str
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String publicKeyDecrypt(String str, String publicKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        PublicKey pubKey =  KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}