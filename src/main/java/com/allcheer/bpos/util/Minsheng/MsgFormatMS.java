package com.allcheer.bpos.util.Minsheng;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by fireWorks on 2016/11/24.
 */
public class MsgFormatMS {
    public static String msgFormat(Map HeaderMap, Map BodyMap){
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<merchant>");
        sb.append("<head>");
        sb.append(buildXMLBody(HeaderMap));
        sb.append("</head>");
        sb.append("<body>");
        sb.append(buildXMLBody(BodyMap));
        sb.append("</body>");
        sb.append("</merchant>");

        return sb.toString();

    }

    protected static String buildXMLBody(Map<String, String> map) {
        Set<String> set = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (Iterator<String> it = set.iterator(); it.hasNext();) {
            String key = it.next();
            String value = map.get(key);
            if (null == value)
                value = "";
            sb.append("<" + key + ">" + value + "</" + key + ">");
        }
        return sb.toString();
    }
}
