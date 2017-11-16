package com.allcheer.bpos.util.task;

import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.service.impl.WechatRegisterServiceImpl;
import com.allcheer.bpos.util.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 * Created by lenovo on 2017/4/27.
 * <p>
 * 翰银批量注册
 */

@Component
@EnableScheduling
public class HanYinRegisterTask {

    private final static Logger logger = LoggerFactory.getLogger(HanYinRegisterTask.class);


    @Autowired
    WechatRegisterService wechatRegisterService;


    // @Scheduled(cron = "0 43 18 28 * ?")
   // @Scheduled(cron = "0 34 14 * * ?")
    public void run() {
        logger.info("===========翰银批量注册  START===========");
        long tm1 = System.currentTimeMillis();
        //List<TblMerInfoDO> tblMerInfoDOList = tblMerInfoDOMapper.selectMerId();
        // List<TblMerInfoDO> tblMerInfoDOList = new ArrayList<>();
        String str = "";

        String dd[] = str.split(",");

        /*for (TblMerInfoDO mer : tblMerInfoDOList) {
            try {
                Map<String, String> msMap = wechatRegisterService.registerForHanyin(mer.getMerId(), WechatRegisterServiceImpl.HAN_YIN_CHANNEL, CommonConstants.OPER_FLAG_M03);
                String errorCode = msMap.get("statusCode");
                String errorMsg = msMap.get("statusMsg");
                // wechatRegisterService.addHanYinMapGen(msMap, WechatRegisterServiceImpl.HAN_YIN_CHANNEL);
                logger.info("=========商户号=========" + mer.getMerId());
                logger.info("=========errorCode=========" + errorCode + "=========errorMsg=========" + errorMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        try {
            File writename = new File("/home/Bpos/Bpos/file/output.txt");
            if (!writename.isFile()) {
                writename.createNewFile(); // 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));


            for (int i = 0; i < dd.length; i++) {
                try {
                    Map<String, String> msMap = wechatRegisterService.registerForHanyin(dd[i], WechatRegisterServiceImpl.HAN_YIN_CHANNEL, CommonConstants.OPER_FLAG_A);
                    String errorCode = msMap.get("statusCode");
                    String errorMsg = msMap.get("statusMsg");

                    wechatRegisterService.addHanYinMapGen(msMap, WechatRegisterServiceImpl.HAN_YIN_CHANNEL);
                    logger.info("=========商户号=========" + dd[i]);
                    logger.info("=========errorCode=========" + errorCode + "=========errorMsg=========" + errorMsg);

                    //将日志读到一个文件里
                    out.write(dd[i] + "===" + errorCode + "===" + errorMsg + "\r"); // \r即为换行

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件


        } catch (Exception e) {
            e.printStackTrace();
        }
        long tm2 = System.currentTimeMillis();
        logger.info("===========END翰银批量注册 ===据耗时: " + (tm2 - tm1) + "毫秒===========");
    }


}
