package com.allcheer.bpos.service;


import com.allcheer.bpos.domain.FuncBO;
import com.allcheer.bpos.util.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by fireWorks on 2016/2/3.
 */
public interface FuncService {

    Pagination<FuncBO> getFuncList(String rid);

    List<FuncBO> getAllFuncList(String rid);

    Map setFuncEnable(String funcId) throws Exception;

    Map setFuncDisable(String funcId) throws Exception;

    Pagination<FuncBO> getTheFunc(FuncBO funcBO);

    Pagination<FuncBO> getAllFunc(FuncBO funcBO);

    Map addNewFunc(FuncBO funcBO);

    FuncBO getById(String funcId);

    Map updateFuncInfo(FuncBO funcBO);

}
