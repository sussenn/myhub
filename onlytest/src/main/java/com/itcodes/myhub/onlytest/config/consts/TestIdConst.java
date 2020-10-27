package com.itcodes.myhub.onlytest.config.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestIdConst 对比id 处理
 * @Author sussen
 * @Version 1.0
 * @Data 2020/3/17
 */
public class TestIdConst {

    private static final Map<String, String> handleIdMap = new HashMap<>();

    static {
        handleIdMap.put("xxxxx","ccc");
        handleIdMap.put("aaaaa","bbb");
    }

    //判断入参id是否包含在配置里
    public static boolean containId(String id) {
        return handleIdMap.keySet().contains(id);
    }

    //取出k对应value
    public static String getValue(String key) {
        return handleIdMap.get(key);
    }
}
