package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.TBLOutcomeBankRecordDO;
import com.allcheer.bpos.form.OutcomeTransLogForm;

import javax.servlet.ServletOutputStream;

import java.util.List;

public interface OutcomeLogService {

    List<TBLOutcomeBankRecordDO> getOutcomeTransList(OutcomeTransLogForm form);

    int getOutcomeTransListCount(OutcomeTransLogForm form);
    
    void exportSettlementInfo(List<TBLOutcomeBankRecordDO> transList,
                              ServletOutputStream outputStream) throws Exception;
}
