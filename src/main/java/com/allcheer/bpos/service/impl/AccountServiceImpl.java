package com.allcheer.bpos.service.impl;


import com.allcheer.bpos.entity.ACCOUNTDO;
import com.allcheer.bpos.entity.ACCOUNTDOExample;
import com.allcheer.bpos.mapper.ACCOUNTDOMapper;
import com.allcheer.bpos.service.AccountService;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/1.
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    ACCOUNTDOMapper accountDoMapper;

    @Override
    public Pagination<ACCOUNTDO> queryAccountList(Map map, ACCOUNTDO accountDo) {
        ACCOUNTDOExample accountDoExample = new ACCOUNTDOExample();
        ACCOUNTDOExample.Criteria criteria = accountDoExample.createCriteria();
        if (accountDo != null) {
            if (StringUtils.isNotBlank(accountDo.getAcctNum())) {
                criteria.andAcctNumEqualTo(accountDo.getAcctNum());
            }
            if (StringUtils.isNotBlank(accountDo.getAgentId())) {
                criteria.andAgentIdEqualTo(accountDo.getAgentId());
            }
        }

        int count = accountDoMapper.countByExample(accountDoExample);
        int pageCurrent = Integer.valueOf((String) map.get("pageCurrent"));
        int pageSize = Integer.valueOf((String) map.get("pageSize"));

        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<ACCOUNTDO> accountDoList = accountDoMapper.selectByExample(accountDoExample);
        pagination.addResult(accountDoList);

        return pagination;
    }

    @Override
    public int insertAccount(ACCOUNTDO accountDo) {
        int result = accountDoMapper.insertSelective(accountDo);
        return result;
    }

}
