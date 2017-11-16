package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.OUTCOMERECORDDOExample;
import com.allcheer.bpos.entity.vo.LocalTxEntity;
import com.allcheer.bpos.mapper.OUTCOMERECORDDOMapper;
import com.allcheer.bpos.mapper.TblBtsTransLogDOMapper;
import com.allcheer.bpos.service.InstMerTXService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/10.
 */

@Service
public class InstMerTXServiceImpl implements InstMerTXService {

    @Autowired
    private OUTCOMERECORDDOMapper outcomerecorddoMapper;

    @Autowired
    private TblBtsTransLogDOMapper tblBtsTransLogDOMapper;


    @Override
    public Pagination<OUTCOMERECORDDO> queryList(Map map, OUTCOMERECORDDO outcomerecorddo) {
        OUTCOMERECORDDOExample outcomerecorddoExample = new OUTCOMERECORDDOExample();
        OUTCOMERECORDDOExample.Criteria criteria = outcomerecorddoExample.createCriteria();
        criteria.andChannelIdEqualTo("HY00");
        outcomerecorddoExample.setOrderByClause("mer_date");
        if (outcomerecorddo != null) {
            if (StringUtils.isNotBlank(outcomerecorddo.getMerTransId())) {
                criteria.andMerTransIdEqualTo(outcomerecorddo.getMerTransId());
            }
            if (StringUtils.isNotBlank(outcomerecorddo.getChannelReturnResv2())) {
                criteria.andChannelReturnResv2EqualTo(outcomerecorddo.getChannelReturnResv2());
            }
            if (StringUtils.isNotBlank(outcomerecorddo.getMerId())) {
                criteria.andMerIdEqualTo(outcomerecorddo.getMerId());
            }
            if (StringUtils.isNotBlank(outcomerecorddo.getMerDate())) {
                criteria.andMerDateEqualTo(outcomerecorddo.getMerDate());
            } else {
                criteria.andMerDateEqualTo(DateUtil.getCurrentDate());
            }
            if (StringUtils.isNotBlank(outcomerecorddo.getChannelReturnCode())) {
                criteria.andChannelReturnCodeEqualTo(outcomerecorddo.getChannelReturnCode());
            }

        }

        int count = outcomerecorddoMapper.countByExample(outcomerecorddoExample);
        int pageCurrent = Integer.valueOf((String) map.get("pageCurrent"));
        int pageSize = Integer.valueOf((String) map.get("pageSize"));

        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<OUTCOMERECORDDO> accountDoList = outcomerecorddoMapper.selectByExample(outcomerecorddoExample);
        pagination.addResult(accountDoList);
        return pagination;
    }

    @Override
    public List<LocalTxEntity> list(String date, String ordId) {
        return tblBtsTransLogDOMapper.localSingleTx(date,ordId);
    }

    @Override
    public int delByOrdId(String ordId) {
        OUTCOMERECORDDOExample outcomedoExample = new OUTCOMERECORDDOExample();
        outcomedoExample.createCriteria().andMerTransIdEqualTo(ordId);
        return outcomerecorddoMapper.deleteByExample(outcomedoExample);

    }

}
