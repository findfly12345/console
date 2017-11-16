package com.allcheer.bpos.util.task;

import com.allcheer.bpos.entity.TblMsCheckFileDo;
import com.allcheer.bpos.entity.TblWechatIncomeCheckFileDo;
import com.allcheer.bpos.form.CheckFileForm;
import com.allcheer.bpos.service.CheckWithOutCardService;
import com.allcheer.bpos.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuBin on 2017/1/9.
 */
@Service
public class tradeCheckFileTask {

    @Autowired
    private CheckWithOutCardService checkWithOutCardService;


    public void run() {
        String todayTime = DateUtil.getCurrentYesterDay();
        List<TblMsCheckFileDo> tblMsCheckFileDos = checkWithOutCardService.getMsCheckFile(todayTime);
        if (tblMsCheckFileDos == null || tblMsCheckFileDos.size() == 0) {
            Map<String, String> msMap = checkWithOutCardService.getMs020(DateUtil.removeLineDateString(todayTime));
            if (msMap.size() > 0) {
                CheckFileForm checkFileForm = new CheckFileForm();
                checkFileForm.setFilterDate(todayTime);
                List<TblWechatIncomeCheckFileDo> tblWechatIncomeCheckFileDos = checkWithOutCardService.getDifferenceWithCheckFile(checkFileForm);
                if (tblWechatIncomeCheckFileDos == null || tblWechatIncomeCheckFileDos.size() == 0) {
                    Map resultMap = checkWithOutCardService.compareT1CheckFile(todayTime);
                }
            }
        }
    }
}
