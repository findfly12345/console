package com.allcheer.bpos.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class JsonUtil {

    private static Gson gson = null;

    static {
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        gson = gb.create();
    }

    public static String toJson(Object vo) {
        if (vo == null) {
            return null;
        }

        return gson.toJson(vo);
    }

    /**
     * json字符串转 map
     */
    public static Map<String, String> toMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    /**
     * json字符串 转 对象
     */

    public static Object toObject(String json, Object obj) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return gson.fromJson(json, obj.getClass());
    }


}
