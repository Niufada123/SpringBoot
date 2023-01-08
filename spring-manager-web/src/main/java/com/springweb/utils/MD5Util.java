package com.springweb.utils;

import java.security.MessageDigest;

/**
 * @Title: MD5工具类
 */
public abstract class MD5Util {
    /**
     * 获取指定字符串的md5值
     * @param dataStr 明文
     * @return String
     */
    public static String md5(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte[] s = m.digest();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定字符串的md5值, md5(str+salt)
     * @param dataStr 明文
     * @return String
     */
    public static String md5WithSalt(String dataStr,String salt) {
        return md5(dataStr + salt);
    }

    public static void main(String[] args) {
        String content = "原始内容" ;
		String s = md5(content);
		System.out.println("加密后1："+s);

		String content1 = "原始内容1" ;
		String s1 = md5(content1);
		System.out.println("加密后2："+s1);


//		String s = md5WithSalt(content, "111");
//		System.out.println(s);
//
//		String content1 = "abc" ;
//		String s1 = md5WithSalt(content1, "112");
//		System.out.println(s1);


	}

}