package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblCityDO;
import com.allcheer.bpos.entity.TblProvDO;

import java.util.List;

/**
 * Created by fireWorks on 2016/10/26.
 */
public interface LocalService {

    public List<TblProvDO> getProvList();

    public List<TblCityDO> getCityList(String provinceId);
}
