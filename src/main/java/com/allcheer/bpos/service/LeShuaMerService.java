package com.allcheer.bpos.service;

import c.a.b.leshua.MerAccountInfo;
import c.a.b.leshua.MerBaseInfo;
import com.allcheer.bpos.entity.vo.LeShuaMerInfoVO;
import com.allcheer.bpos.util.Pagination;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by APPLE on 2017/8/29.
 */
@Service
public interface LeShuaMerService {

    Pagination<LeShuaMerInfoVO> getLeShuaMerInfoList(Map map);

    Map register(String memberId);

    Map registerBusiness(String memberId);

    String getReqSerialNo();

    MerBaseInfo getMerBaseInfo(String memberId);

    MerAccountInfo getMerAccountInfo(String memberId);

    Map openShaoMa(String memberId, String merchantId);

}
