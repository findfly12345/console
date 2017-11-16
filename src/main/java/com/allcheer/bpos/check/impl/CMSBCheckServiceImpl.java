package com.allcheer.bpos.check.impl;

import com.allcheer.bpos.check.CheckService;
import com.allcheer.bpos.check.entity.constant.TransStatus;
import com.allcheer.bpos.entity.GateBankCheckDataDO;
import com.allcheer.bpos.util.SftpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("cmsbcheckserviceimpl")
public class CMSBCheckServiceImpl extends CheckService {
	private Logger logger = LoggerFactory.getLogger(CMSBCheckServiceImpl.class);

	private static final String CHARSET = "UTF-8";

	@Override
	public List<GateBankCheckDataDO> parseBankFile(InputStream in) {
		logger.debug("民生银行，解析数据");
		List<GateBankCheckDataDO> checkDataList = new ArrayList<GateBankCheckDataDO>();
		GateBankCheckDataDO checkData = null;
		String tempbf;
		String[] split;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, CHARSET));
			while ((tempbf = br.readLine()) != null) {
				split = tempbf.split(",");
				if (split.length == 11) {
					checkData = new GateBankCheckDataDO();
					checkData.setSeqId(split[7]);
					checkData.setTransDate(split[2]);

					checkData.setTransStatus(TransStatus.S.toString());
					checkData.setTransAmt(split[5]);
					checkData.setAcctId(split[4]);
					checkDataList.add(checkData);
				}
			}
		} catch (Exception e) {
			logger.error("解析对帐文件失败", e);
		}
		return checkDataList;
	}

	@Override
	public InputStream getBankFile(Map<String, String> config, String checkDate) {
		String ip = config.get("ip");
		int port = Integer.parseInt(config.get("port"));
		String user = config.get("user");
		String passwd = config.get("passwd");

		String sftpDir = config.get("ftp_dir");

		String inst = config.get("inst");
		String fileName = "DZ" + inst + checkDate + ".txt";

		SftpUtil sftp = new SftpUtil(ip, port, user, passwd, null);
		try {
			return sftp.getFile(sftpDir, fileName);
		} catch (Exception e) {
			logger.error("获取文件{}失败",fileName,e);
		}
		return null;
	}

}
