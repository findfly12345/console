package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.vo.LocalTxEntity;
import com.allcheer.bpos.util.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/10.
 */
public interface InstMerTXService {

    Pagination<OUTCOMERECORDDO> queryList(Map map,OUTCOMERECORDDO outcomerecorddo);

    List<LocalTxEntity> list(String date, String ordId);

     int delByOrdId(String ordId);


}
