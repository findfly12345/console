package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblBTSInstStatDO;
import com.allcheer.bpos.entity.TblBTSInstStatDOExample;
import com.allcheer.bpos.mapper.TblBTSInstStatDOMapper;
import com.allcheer.bpos.service.SecretKeyService;
import com.allcheer.bpos.util.HsmApp;
import com.allcheer.bpos.util.constant.HtsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fireWorks on 2016/3/1.
 */
@Service("secretKeyService")
public class SecretKeyServiceImpl implements SecretKeyService {
    @Autowired
    TblBTSInstStatDOMapper instStatDOMapper;

    private static final Logger logger = LoggerFactory.getLogger(SecretKeyServiceImpl.class);
    @Override
    public String getSecretKeyByIndex(int index) {
        HsmApp hsmApp = new  HsmApp();
        logger.debug("in getSecretKeyByIndex. Before getKey");
        String res = hsmApp.HSMEncryptBmkToLmkD10D(index);
        logger.debug("in getSecretKeyByIndex. After getKey   Key:" + res);
        return res;
    }

    /**
     * @see com.allcheer.bpos.service.SecretKeyService#insertInstSecretKey(String, String, String)
     */
    @Override
    public  String insertInstSecretKey(String instCode, String index, String secretKey) {
        TblBTSInstStatDOExample instStatDOExample = new TblBTSInstStatDOExample();
        instStatDOExample.createCriteria().andInstCodeEqualTo(instCode);
        List<TblBTSInstStatDO> tblBTSInstStatDOList = instStatDOMapper.selectByExample(instStatDOExample);

        TblBTSInstStatDO instStatDO = new TblBTSInstStatDO();
        instStatDO.setInstCode(instCode);
        instStatDO.setInstPrimKey(secretKey);
        instStatDO.setInstHsmIndex(index);

        if(tblBTSInstStatDOList == null || tblBTSInstStatDOList.size() == 0){

            try {
                instStatDOMapper.insert(instStatDO);
                return HtsConstant.SUC_CODE;
            } catch (Exception e) {
                logger.error("修改机构密钥失败", e);
                return HtsConstant.ERR_CODE;
            }

        }else{
            try {
                instStatDOMapper.updateByPrimaryKeySelective(instStatDO);
                return HtsConstant.SUC_CODE;
            } catch (Exception e) {
                logger.error("修改机构密钥失败", e);
                return HtsConstant.ERR_CODE;
            }

        }


    }

}
