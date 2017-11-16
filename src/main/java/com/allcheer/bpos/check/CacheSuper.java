package com.allcheer.bpos.check;

import java.util.Map;

public interface CacheSuper {

    boolean batchPut(String key, Map<String, String> value);

    String getMapValue(String name, String key);

    boolean dorp();

}
