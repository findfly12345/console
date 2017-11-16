package com.allcheer.bpos.constant;

public class SystemConstant extends ConfigurableContants {

	static {
		init("/system.properties");
	}

	public static final String USER_SESSION_KEY = "User_Session_key";
	
	public static String ENCRYPT_HEX_STRING = getProperty("encrypt.hex.string");
	public static String BATCHUP_TEMPLATE_FILE = getProperty("batchup.template.file");
	public static String FILE_TEMP_DIR = getProperty("file.temp.dir");

	// check config
	public static String IP_SERVICE = getProperty("redis.ip");
	public static String PORT_SERVICE = getProperty("redis.port");
	public static String CHECK_FILE_DIR = getProperty("check.file.dir");


	public static String ADD_INST_MER_FILE_PATH = getProperty("add.inst.mer.file.path");

	public static String ADD_AGENT_MER_FILE_PATH = getProperty("add.agent.mer.file.path");

	public static String ADD_INST_MER_RESULT_FILE_TEMP= getProperty("add.inst.mer.result.file.temp");

	public static String ADD_INST_MER_RESULT_FILE= getProperty("add.inst.mer.result.file");

	public static String ADD_INST_MER_ATTACHMENT_FILE_PATH = getProperty("add.inst.mer.attachment.file.path");

	public static String ADD_AGENT_MER_ATTACHMENT_FILE_PATH = getProperty("add.agent.mer.attachment.file.path");

	public static String MER_ATTACHMENT_FILE_PATH = getProperty("mer.attachment.file.path");

	public static String WC_INCOME_EXCEL_PATH = getProperty("wc.income.excel.path");


	public static String QIANBAO_MER_CHANNEL_PRE_TEMP= getProperty("qianbao.mer.channel.pre.temp");

	public static String QIANBAO_INST_ID = getProperty("qianbao.inst.id");

	public static String MER_CHANNEL_PRE_PATH = getProperty("mer.channel.pre.path");

	public static String MER_CHANNEL_PRE_RESULT_PATH = getProperty("mer.channel.pre.result.path");

	public static String QIANBAO_CHECK_FILE_PATH = getProperty("qianbao.check.file.path");

	//民生银行配置
	public static String MSBANK_BASE_URL = getProperty("msbank_base_url");
	public static String MSBANK_INST_ID = getProperty("msbank_inst_id");
	public static String MSBANK_RSA_PUB = getProperty("MSBANK_RSA_PUB");
	public static String SELF_RSA_PRV = getProperty("SELF_RSA_PRV");
	public static String SELF_AES_KEY = getProperty("SELF_AES_KEY");
	public static String MSBANK_NOTIFY_URL = getProperty("MSBANK_NOTIFY_URL");

	//翰银通道配置
	public static String HAN_YIN_URL = getProperty("han_yin_url");
	public static String HAN_YIN_INST_MER_NUMBER = getProperty("han_yin_inst_mer_number");
	public static String HAN_YIN_INST_CODE = getProperty("han_yin_inst_code");
	public static String HAN_YIN_KEY = getProperty("han_yin_key");
	public static String HAN_YIN_TX = getProperty("han_yin_tx");


	public static String TRADE_EXPORT_FILE = getProperty("trade.export.file");
	
	/**
	 * 商户二维码路径
	 */
	public static String MER_QRCODE_FOLDER = getProperty("MER_QRCODE_FOLDER");
	/**
	 * 在线支付地址
	 */
	public static String ONLINE_PAY_URL = getProperty("ONLINE_PAY_URL");
	/**
	 * 二维码LOGO地址
	 */
	public static String PAY_LOGO_PATH = getProperty("PAY_LOGO_PATH");	
	
	/**
	 *没有找到二维码
	 */
	public static String QRCODE_NO_FOUND = getProperty("QRCODE_NO_FOUND");	
	/**
	 *生成二维码
	 */
	public static String TO_CREATE_QRCODE = getProperty("TO_CREATE_QRCODE");	

	/**
	 * online服务地址
	 */
	public static String ONLINE_BASE_URL = getProperty("ONLINE_BASE_URL");	
	
	/**
	 * 商户管理中心地址
	 */
	public static String MER_CENTER_URL = getProperty("MER_CENTER_URL");

	public static String LE_SHUA_AGENT_ID = getProperty("leshua.agentId");

	public static String LE_SHUA_MD5_KEY = getProperty("leshua.md5Key");

	public static String LE_SHUA_MER_URL = getProperty("leshua.merUrl");

}
