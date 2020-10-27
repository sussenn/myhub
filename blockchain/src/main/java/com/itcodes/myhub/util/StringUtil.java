package com.itcodes.myhub.util;

import com.google.gson.GsonBuilder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @ClassName StringUtil
 * @Author sussen
 * @Version 1.0
 * @Data 2019/12/16
 */
public class StringUtil {

    //将Sha256应用于字符串并返回结果
    public static String applySha256(String input) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //将sha256应用于 输入值,获取输入值的哈希
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 这里 将包含哈希作为十六进制
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //将对象转换为json字符串的快捷助手
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //返回难度字符串目标，用于与哈希进行比较。 例如，难度5将返回"00000"
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }
}
