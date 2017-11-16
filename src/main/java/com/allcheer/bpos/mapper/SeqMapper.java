package com.allcheer.bpos.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by fireWorks on 2016/2/26.
 */
public interface SeqMapper {

    int getTblBTSSysUsrIdSeq();

    int getTblBTSSysRoleIdSeq();

    int getTblBTSSysFuncIdSeq();

    int getTblBTSInstIdSeq();

    int getMerTradeCountRuleSeq();
    
    int getLoginUsrSeq();

    long getSequenceNextVal(@Param("seqName") String seqName);
}
