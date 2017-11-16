package com.allcheer.bpos.service;



import com.allcheer.bpos.entity.ACCOUNTDO;
import com.allcheer.bpos.util.Pagination;

import java.util.Map;

/**
 * Created by peng.ll on 2017/3/1.
 */
public interface AccountService {

     Pagination<ACCOUNTDO> queryAccountList(Map map, ACCOUNTDO accountDo);

     int insertAccount(ACCOUNTDO accountDo);


}
