package com.allcheer.bpos.util.task;

import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.WCINCOMEDO;
import com.allcheer.bpos.form.WCTransLogForm;
import com.allcheer.bpos.service.BtsInstService;
import com.allcheer.bpos.service.WCTransLogService;
import com.allcheer.bpos.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Created by LiuBin on 2017/1/4.
 */
@Service
public class exportOrdersDetailInfoTask {
	private final static Logger logger = LoggerFactory.getLogger(exportOrdersDetailInfoTask.class);

	@Autowired
	WCTransLogService wctransLogService;

	@Autowired
	BtsInstService btsInstService;

	public void run() {
		String path = "";

		logger.info("====================生成对账文件=========================");
		// 目录检查
		List<TblBtsInstDO> tblBtsInstDOList = createDir();

		for (TblBtsInstDO tblBtsInstDO : tblBtsInstDOList) {
			WCTransLogForm trans = new WCTransLogForm();
			StringBuilder fileContent = new StringBuilder();
			trans.setInstId(tblBtsInstDO.getInstCode());
			trans.setStartTransDateTime("20170716");
			trans.setEndTransDateTime("20170716");
			trans.setPageSize("10000");
			trans.setTransStat("S");
			List<WCINCOMEDO> transList = wctransLogService.getTransListForExport(trans);
			String todayTime = DateUtil.getCurrentYesterDay();
			String fileName = "";
			path = SystemConstant.TRADE_EXPORT_FILE;
			System.out.println("path-------->" + path);
			File file = new File(path);
			try {
				if (!file.exists()) {
					file.mkdirs();
				}
				String instId = tblBtsInstDO.getInstCode();
				path = path + instId + "/";
				fileName = instId + "-" + todayTime;

				for (WCINCOMEDO wcincomedo : transList) {
					fileContent.append(wcincomedo.getIncomeId()).append("|").append(wcincomedo.getOutOrderId()).append("|").append(wcincomedo.getIncomeType())
							.append("|").append(wcincomedo.getIncomeRecvTime()).append("|").append(wcincomedo.getIncomeAmount())
							.append("|").append(wcincomedo.getInstId()).append("|").append(wcincomedo.getMemberId())
							.append("|").append(wcincomedo.getIncomeStatus()).append("|")
							.append(wcincomedo.getClearDate()).append("|").append("\r\n");
				}

				if (fileContent.length() > 0) {
					file = new File(path + fileName);
					if (!file.exists()) {
						logger.debug("创建文件");
						file.createNewFile();
					}
					logger.debug("写文件: " + fileContent.toString());

					writeFileContent(path + fileName, fileContent.toString());
					fileContent = new StringBuilder();
				}
			} catch (Exception e) {
				logger.debug("创建文件异常");
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 向文件中写入内容
	 * 
	 * @param filepath
	 *            文件路径与名称
	 * @param newstr
	 *            写入的内容
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath, String newstr) throws IOException {
		Boolean bool = false;
		String filein = newstr;// 新写入的行，换行

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			File file = new File(filepath);// 文件路径(包括文件名称)
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(filein);
			pw.flush();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 不要忘记关闭
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return bool;
	}

	/**
	 * 检查-创建机构目录
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<TblBtsInstDO> createDir() {

		String path = "";
		String dirPath = "";

		path = SystemConstant.TRADE_EXPORT_FILE;

		logger.debug("遍历机构表获取机构信息");
		List<TblBtsInstDO> tblBtsInstDOList = btsInstService.selectAllInstInfo();
		for (TblBtsInstDO tblBtsInstDO : tblBtsInstDOList) {
			dirPath = path + tblBtsInstDO.getInstCode();
			File fileName = new File(dirPath);

			if (fileName.exists()) {
				logger.debug("目录已存在");
			} else {
				fileName.mkdirs();
				logger.debug("创建新目录: " + dirPath);
			}
		}

		return tblBtsInstDOList;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getCurrentYesterDay());
	}
}