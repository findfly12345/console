package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.TblMerQrcode;
import com.allcheer.bpos.entity.TblMerQrcodeExample;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TblMerQrcodeMapper;
import com.allcheer.bpos.service.QrcodeService;
import com.allcheer.bpos.util.QRCodeUtil;
import com.allcheer.bpos.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/1.
 */

@Service("QrcodeService")
public class QrcodeServiceImpl implements QrcodeService {

	@Autowired
	SeqMapper seqMapper;
	
	@Autowired
	TblMerQrcodeMapper tblMerQrcodeMapper;

    private static final Logger logger = LoggerFactory.getLogger(QrcodeServiceImpl.class);
    
	@Override
	public Map<String, String> QrcodeCreate(String instId, String agentId, String merId, String termId) {
		
		Map<String, String> returnMap = new HashMap<String, String>(); 
		
		logger.debug("生成二维码");
		String qrcodePath = "";
		String qrcodeName = "";
		if (StringUtils.isNotBlank(instId)) {
		    qrcodePath = SystemConstant.MER_QRCODE_FOLDER + instId + "/";
		    qrcodeName = "inst-mer-";
		} else if (StringUtils.isNotBlank(agentId)) {
		    qrcodePath = SystemConstant.MER_QRCODE_FOLDER + agentId + "/";
		    qrcodeName = "agent-mer-";
		} else {
			qrcodePath = SystemConstant.MER_QRCODE_FOLDER + "/";;	
			qrcodeName = "mer-";
		}

		if(StringUtils.isBlank(termId)){
			termId = "00000000";
		}
		String payUrl = SystemConstant.ONLINE_PAY_URL + "?MERID=" + merId + "&TERMID=" + termId;
		String logoPath = SystemConstant.PAY_LOGO_PATH;
		
		File qrcodeFolder = new File (qrcodePath); 		
		if (!qrcodeFolder.exists()) {
			qrcodeFolder.mkdirs();
		}
		qrcodeName = qrcodeName + merId + ".png";	
		String qrcodeImageLocation = "";
		
		try {
			qrcodeImageLocation = QRCodeUtil.genQRCodeWithLog(payUrl, logoPath, qrcodeName, qrcodePath, true);
			returnMap.put("imageLocation", qrcodeImageLocation);
					
		} catch (Exception e) {
			logger.error("生成二维码失败" + e.getMessage());
			e.printStackTrace();
		}
			
		return returnMap;
	}
	

	/**
	 * 保存二维码信息
	 * @param merId
	 * @param instId
	 * @param agentId
	 * @param qrcode
	 * @param qrcodePath
	 * @return
	 */
	@Override
	public Boolean QrcodeSaving (String merId, String instId, String agentId, String qrcode, String qrcodePath) {

		
		TblMerQrcode tblMerQrcodeExist = tblMerQrcodeMapper.selectByPrimaryKey(merId);		
		
		TblMerQrcode tblMerQrcode = new TblMerQrcode();
		
		tblMerQrcode.setMerId(merId);
		tblMerQrcode.setQrcodePath(qrcodePath);

		if(StringUtils.isNotBlank(instId)){
			tblMerQrcode.setInstId(instId);
		}
		if(StringUtils.isNotBlank(agentId)){
			tblMerQrcode.setAgentId(agentId);
		}
		if(StringUtils.isNotBlank(qrcode)){
			tblMerQrcode.setQrcode(qrcode);
		}

		int count = 0;
		if(tblMerQrcodeExist == null) {
		    count = tblMerQrcodeMapper.insert(tblMerQrcode);
		} else {
			TblMerQrcodeExample tblMerQrcodeExample = new TblMerQrcodeExample();
			tblMerQrcodeExample.createCriteria().andMerIdEqualTo(merId);
			count = tblMerQrcodeMapper.updateByExample(tblMerQrcode, tblMerQrcodeExample);
		}
				
		if (count > 0) {			
			return true;
		}	
		return false; 
	}
	
}
