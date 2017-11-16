package com.allcheer.bpos.mapper;

import com.allcheer.bpos.domain.LeShuaMerInfoBO;

import java.util.List;
import java.util.Map;

public interface LeShuaMerInfoCustDOMapper {

    int countLeShuaMerInfoList(Map map);

    List<LeShuaMerInfoBO> getLeShuaMerInfoList(Map map);

}