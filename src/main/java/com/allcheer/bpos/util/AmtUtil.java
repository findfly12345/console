package com.allcheer.bpos.util;

import com.allcheer.bpos.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 金额工具类
 */
public class AmtUtil {

	private static final Logger logger = LoggerFactory.getLogger(AmtUtil.class);

	private AmtUtil() {
	}

	/**
	 * 金额格式化，显示两位小数（只舍不入）
	 */
	public static String amtFormat(BigDecimal b) {
		if (b == null)
			return Constant.AMT_DEFAULT;

		DecimalFormat df = new DecimalFormat(Constant.AMT_DEFAULT);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(b);
	}

	/**
	 * 金额格式化（只舍不入）
	 */
	public static String amtFormat(BigDecimal b, String format) {
		if (b == null)
			return Constant.AMT_DEFAULT;

		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(b);
	}

	/**
	 * 金额格式化，显示两位小数
	 */
	public static String amtFormat(double d) {
		return amtFormat(new BigDecimal(d));
	}

	/**
	 * 金额格式化，显示两位小数
	 */
	public static String amtFormat(String s) {
		if (s == null || s.trim().equals(""))
			return Constant.AMT_DEFAULT;

		return amtFormat(new BigDecimal(s));
	}

	/**
	 * 提供精确的加法运算
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的加法运算，返回格式化后的字符串
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static String add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtFormat(b1.add(b2));
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static String subtract(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtFormat(b1.subtract(b2));
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtFormat(b1.multiply(b2));
	}

	/**
	 * 提供精确的乘法运算，结果去除小数
	 */
	public static String multiplyWithIntFormat(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtFormat(b1.multiply(b2), Constant.AMT_INT);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积，不格式化
	 */
	public static String multiplyWithoutFormat(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtFormat(b1.multiply(b2), Constant.AMT_FORMAT);
	}

	/**
	 * 除法运算，精确两位小数，多余的去除
	 */
	public static String divide(BigDecimal b1, BigDecimal b2) {
		return amtFormat(b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 除法运算，精确两位小数，多余的去除
	 */
	public static String divide(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return divide(b1, b2);
	}

	/**
	 * 除法运算
	 */
	public static String divideWithoutFormat(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtFormat(b1.divide(b2), Constant.AMT_FORMAT);
	}

	/**
	 * 数值比较
	 * 
	 * -1 小于 0 等于 1 大于
	 */
	public static int compareTo(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return b1.compareTo(b2);
	}

	/**
	 * 数值比较
	 * 
	 * -1 小于 0 等于 1 大于
	 */
	public static int compareTo(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return b1.compareTo(b2);
	}

	/**
	 * 数值比较
	 * 
	 * -1 小于 0 等于 1 大于
	 */
	public static int compareTo(BigDecimal b1, BigDecimal b2) {
		return b1.compareTo(b2);
	}

	/**
	 * 交易金额校验
	 * 
	 * null - 数据校验错误 not null - 格式化好的字符串
	 */
	public static String getTranAmt(String tranAmt) {
		if (tranAmt == null) {
			logger.error("tran amt = null");
			return null;
		}

		try {
			BigDecimal amtBD = new BigDecimal(tranAmt);
			String amtStr = AmtUtil.amtFormat(amtBD);

			if (AmtUtil.compareTo(amtBD, new BigDecimal(amtStr)) == 0) {
				return amtStr;

			} else {
				logger.error("tran amt err: " + tranAmt);
				return null;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 金额格式化（只舍不入）
	 */
	public static String amtTwoFormat(BigDecimal b, String format) {
		if (b == null)
			return Constant.AMT_DEFAULT;

		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.UP);
		return df.format(b);
	}

	public static String multiplyWithoutTwoFormat(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return amtTwoFormat(b1.multiply(b2), Constant.AMT_DEFAULT);
	}

}
