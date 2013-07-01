package com.elliot.framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具类
 * User: andy
 * Date: 13-11-17
 * @since: 1.0
 */
public class DecodeUtil {
    /**
     * MD5加密方法
     * @param str  需要加密的字符串
     * @return String 返回加密后的字符串
     */
    public static String md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
        byte[] resultByte = messageDigest.digest(str.getBytes());
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < resultByte.length; ++i) {
            result.append(Integer.toHexString(0xFF & resultByte[i]));
        }
        return result.toString();
    }
}
