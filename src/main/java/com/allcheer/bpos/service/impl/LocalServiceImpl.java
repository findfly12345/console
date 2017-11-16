package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblCityDO;
import com.allcheer.bpos.entity.TblCityDOExample;
import com.allcheer.bpos.entity.TblProvDO;
import com.allcheer.bpos.entity.TblProvDOExample;
import com.allcheer.bpos.mapper.TblCityDOMapper;
import com.allcheer.bpos.mapper.TblProvDOMapper;
import com.allcheer.bpos.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fireWorks on 2016/10/26.
 */
@Service("localService")
public class LocalServiceImpl implements LocalService {


    @Autowired
    TblProvDOMapper provDOMapper;

    @Autowired
    TblCityDOMapper cityDOMapper;

    @Override
    public List<TblProvDO> getProvList(){
        List provList = new ArrayList();

        TblProvDOExample provinceExample = new TblProvDOExample();
        provinceExample.createCriteria();
        List<TblProvDO> provinceList = provDOMapper.selectByExample(provinceExample);

        if(provinceList.size() == 0){
            return null;
        }

        return provinceList;
    }

    @Override
    public List<TblCityDO> getCityList(String provinceId){
        List provList = new ArrayList();

        TblCityDOExample cityExample = new TblCityDOExample();
        cityExample.createCriteria().andProvinceIdEqualTo(provinceId);
        List<TblCityDO> cityList = cityDOMapper.selectByExample(cityExample);

        if(cityList.size() == 0){
            return null;
        }
        return cityList;
    }
}
