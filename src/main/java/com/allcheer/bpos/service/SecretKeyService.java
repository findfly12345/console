package com.allcheer.bpos.service;

/**
 * Created by fireWorks on 2016/3/1.
 */
public interface SecretKeyService {

    public  String getSecretKeyByIndex(int index);

    public String insertInstSecretKey(String instCode, String index, String secretKey);

}
