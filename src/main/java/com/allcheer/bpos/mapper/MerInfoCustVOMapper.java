package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;

import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/10/22.
 */
public interface MerInfoCustVOMapper {

    List<TblMerInfoVO> queryMerInfo(Map map);

    int countMerInfo(Map map);

    List<TblAgentMerInfoVO> queryAgentMerInfo(Map map);

    int countAgentMerInfo(Map map);
}
