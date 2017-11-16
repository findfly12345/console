package com.allcheer.bpos.service;

import com.allcheer.bpos.domain.*;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerRelevanceMccDo;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.util.Pagination;

import javax.servlet.ServletOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by fireWorks on 2016/2/27.
 */
public interface InstitutionService {
    List<TblBtsInstDO> getALLInst();

    public Pagination<InstBO> getTheInst(InstBO instBO);

    public int addNewInst(InstBO instBO);

    public int updateInstInfo(InstBO instBO);

    public Pagination<UserBO> getInstUsr(UserBO userBO);

    public Map resetPass(UserBO userBO);

    public Map disableUser(UserBO userBO);

    public TblBtsInstDO getInstById(String instCode);

//    public List<InstStatBO> getInstSecKeyList();

    public Pagination<InstMerBO> getMerByInst(InstMerBO instMerBO);

    public Map addMerForInst(InstMerBO instMerBO);

    public Pagination<InstMerKeyBO> getInstSecKeyList(InstMerKeyBO instMerKeyBO);

    public Map toggleMapStatus(InstChannelMapBO instChannelMapBO);

    public Map addInstChannelMap(InstChannelMapBO instChannelMapBO);

    public Pagination<InstChannelMapBO> getInstChannelMapList(InstChannelMapBO instChannelMapBO);

    public boolean isInstExit(InstBO instBO);

    public Map addInstMerKeyList( List<List<Object>> uploadFileList,String instType);

    public Map addInstChannelMapList (List<List<Object>> uploadFileList);

    public Map deleteInstById(String id);

    public Map deleteMerForInst(String instCode,String merId);

    public Map deleteInstChannelMap(String instCode,String merId,String termId);

	public Map openNewInst(InstBO instBO, String instLoginUser);

	public List<TblMerInfoVO> queryInstMerList(Map map);

	public void exportInstMerList(List<TblMerInfoVO> tblMerInfoVOList, ServletOutputStream outputStream) throws Exception;

	public List<TblMerFeeInfoDO> queryMerFeeById(String merId);

	public TblMerBankInfoDO queryMerBankById(String merId);

	public TblMerRelevanceMccDo queryTblMerRelevanceMccDoById(String merId);


}
