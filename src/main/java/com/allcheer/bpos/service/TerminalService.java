package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblAgentMerTermDo;
import com.allcheer.bpos.entity.TblInstMerKeyInfoDO;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblTermInfoDO;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.form.TerminalForm;
import com.allcheer.bpos.util.Pagination;

import java.util.Map;

/**
 * Created by LiuBin on 2017/2/8.
 */
public interface TerminalService {


        public Pagination<TblTermInfoDO> queryTermInfoList(TerminalForm terminalForm);

        public String queryAgetIdByMer(String merId);
        
        public Map insertTblTermInfo(TblTermInfoDO tblTermInfoDO);

        public TblTermInfoDO queryTermById (String termId);

        public Map updateTblTermInfo (TblTermInfoDO tblTermInfoDO);

        public Map updateTblAgentMerTerm (TblAgentMerTermDo tblAgentMerTermDo);

        public Map bindTblAgentMerTerm (TblAgentMerTermDo tblAgentMerTermDo);

        public Pagination<TblMerInfoDO> queryMerInfoList(MerInfoForm merInfoForm);

        public int insertInstMerKey(TblInstMerKeyInfoDO tblInstMerKeyInfoDO);


        public String getPlatFormTermId();

}
