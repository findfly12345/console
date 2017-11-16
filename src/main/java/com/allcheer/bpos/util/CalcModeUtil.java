package com.allcheer.bpos.util;

import com.allcheer.bpos.constant.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by APPLE on 2016/10/20.
 */
public class CalcModeUtil {
    /**
     * 拆分签约手续费公式
     * AMT*0.009
     * MTM(0,50,AMT*0.009)
     * MIN(50,AMT*0.009)
     * @param conRate
     * @return 返回数据型式：rate,max,min
     */
    public static String splitCalcMode(String conRate, boolean isPercent) {
        conRate = conRate.trim();
        String regex = "\\d+(\\.\\d+)?";
        List<String> calcList = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(conRate);
        for (int i = 0; i < 3; i++) {
            if (m.find()) {
                calcList.add( m.group() );
            }
        }

        String result = null;
        String rate = calcList.get( calcList.size()-1 );
        if (isPercent) {
            rate = AmtUtil.multiplyWithoutFormat(rate, Constant.STRING_100);
        }

        switch (calcList.size()) {
            case 1:
                result = rate + ",0,0";
                break;

            case 2:
                result = rate + "," + calcList.get(0) + ",0";
                break;

            case 3:
                result = rate + "," + calcList.get(1) + "," + calcList.get(0);
                break;

            default:
                result = Constant.STRING_EMPTY;
                break;
        }

        return result;
    }

    /**
     * 生成手续费公式
     * AMT*0.009
     * MTM(0,50,AMT*0.009)
     * MIN(50,AMT*0.009)
     */
    public static String genCalcMode(String rate, String max, String min, boolean isPercent) {
        // 公式
        String[] calc = {"AMT*[RATE]", "MTM([MIN],[MAX],AMT*[RATE])", "MIN([MIN],AMT*[RATE])"};

        // 是否百分比数字
        if (isPercent) {
            rate = AmtUtil.divideWithoutFormat(rate, Constant.STRING_100);
        }

        int mode = 0;
        String result = null;

        if ( max != null ) {
            mode = 1;
            result = calc[mode].replace("[MIN]", min).replace("[MAX]", max).replace("[RATE]", rate);

        } else if ( min != null ) {
            mode = 2;
            result = calc[mode].replace("[MIN]", min).replace("[RATE]", rate);

        } else {
            result = calc[mode].replace("[RATE]", rate);
        }

        return result;
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     *            指定字符串长度
     * @return
     */
    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                cResult[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                cResult[i] = (char) ('a' + Math.random() * 26);
            else
                cResult[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }

    public static void main(String args[]) {
        System.out.print(splitCalcMode("AMT*0.01",false).split(",")[0]);
    }
}
