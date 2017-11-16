/**
 * 
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allcheer.bpos.util.Base64Util;
import com.allcheer.bpos.util.CryptUtil;
import com.allcheer.bpos.util.MD5Util;

/**
 * @author Administrator
 *
 */
public class ControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
	
	public void testOutcome() throws Exception {
		String code="";
		String[] keys = { "instId","merId","instMerId","picAddress1","picAddress2","picAddress3","picAddress4","picAddress5","picAddress6","picAddress7","picAddress8","picAddress9","picAddress10","picAddress11","picAddress12","picAddress13","picAddress15"};
		String[] params = { "00000022", "201701090002638", "15985747133009",code,code,code,code,code,code,code,code,code,code,code,code,code,code};
		build(keys, params, "intoPicAddress");
	}
	
	public void testMerEnter() throws Exception{
		
		/*
		String[] keys = {"payType", "insNum", "merName", "merAddress", "merProvince", "merCity", "merArea", "merStat", "merType", "regShortName",
                "funcStat", "legalPerson", "legalPersonCertType", "legalPersonCertNm", "legalPersonCertExpire", 
                "contactPerson", "contactMobile", 
                "bankName", "bankBranchName", "isPrivate", "provName", "cityName", 
                "debitCardName","debitCardNum","debitCardLines",
                "debitCardFee", "debitCardMax", "creditCardFee",
                "WXT1","WXT0","ZFBT0","ZFBT1",
                "factorageT0","factorageT1"};



	String[] params = {"04", "00000008", "和平", "上海市真光路", "上海", "上海市", "普陀区", "1", "1", "和平",
                "YYYYYYYYYY", "和平", "0", "41142519850821453X", "20500101", 
                "和平", "13817291731", 
                "中国工商银行", "中国工商银行真光路支行", "Y", "上海", "上海市", 
                "和平","5653434343435454","8876287621",
                "0.21", "9999", "0.21",
                "0.21","0.21","0.21","0.21",
                "0.21","0.21"};	
		*/
		
		String[] keys = {"payType", "insNum", "merName", "merAddress", "merProvince", "merCity", "merArea", "merStat", "merType", "regShortName",
                "funcStat", "legalPerson", "legalPersonCertType", "legalPersonCertNm", "legalPersonCertExpire", "busLincenNumber", "busLincenExpireDate", "taxRegCert",
                "contactPerson", "contactMobile", 
                "bankName", "bankBranchName", "isPrivate", "provName", "cityName", 
                "debitCardName","debitCardNum","debitCardLines",
                "debitCardFee", "debitCardMax", "creditCardFee",
                "WXT1","WXT0","ZFBT0","ZFBT1",
                "factorageT0","factorageT1",
                "qcPayFeeT1","qcPayFeeRateT1",
                "qcWithDrawFeeT0","qcWithDrawFeeRateT0",
                "outMerId","outTermNo","outTermName", "outTermType","outTermProv", "outTermCity","outTermArea", "outTermAddress", "outTermSN"               		
		};

	    String[] params = {"05", "00000008", "中鹰物业测试", "上海市普陀区真光路1228号", "310000", "310100", "310107", "1", "2", "中鹰物业测试",
                "YYYYYYYYYY", "殷越泽", "0", "441881197302159356", "20990101", "", "", "",
                "魏万坤", "13817291731", 
                "招商银行", "招商银行古北支行", "Y", "上海", "上海市", 
                "殷越泽","6225882119734638","308290003302",
                "0.01", "9999", "0.21",
                "0.91","0.21","0.21","0.21",
                "","0.21",
                "0.01","0.01",
                "0.01","0.01",
                "0000012121","T00001","百福", "0","上海市", "上海市","普陀区", "普陀区真光路1228号", "SN000001" };			
		
		build(keys, params, "enter");
	}
	
	
	private void build(String[] keys, String[] params, String service) throws UnsupportedEncodingException {
		String str = _MakeURL(keys, params);
		System.out.println(str);
		String sign = MD5Util.getMD5Str(str + "1BBD465ABC0BBE63");

		logger.info("after MD5 : " + sign);

		StringBuilder sb = new StringBuilder(str.toString());
		sb.append("&");
		sb.append("sign");
		sb.append('=');
		sb.append(sign);
		System.out.println(sb.toString());
		
		String paramStr = CryptUtil.GetEncodeStr(sb.toString());

		String finalStr  = Base64Util.encode(paramStr.getBytes());
		try {
			finalStr = filter(finalStr);
			finalStr = URLEncoder.encode(finalStr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		String param = "sText=" +finalStr;
		System.out.println(param);

		InputStream is = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			URL url = new URL("http://localhost:8088/Bpos/mer/" + service);
			URLConnection urlConnection = url.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("GET");

			httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");

			httpUrlConnection.connect();

			DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();

			int resultCode = httpUrlConnection.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb1 = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpUrlConnection.getInputStream()));
				while ((readLine = responseReader.readLine()) != null) {
					sb1.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb1.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpUrlConnection != null) {
				httpUrlConnection.disconnect();
			}
		}
	}

	public static String _MakeURL(String[] keys, String[] params) {
		if (keys.length != params.length) {
			return null;
		}

		StringBuilder url = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			url.append('&');
			url.append(keys[i]);
			url.append('=');
			url.append(params[i]);
		}

		return url.toString().replaceFirst("&", "");
	}
	
	private static String filter(String str) throws UnsupportedEncodingException {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if ((asc != 10) && (asc != 13))
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb.toString().getBytes(), "UTF-8");
		return output;
	}
	
	public static void main(String[] args) {
		ControllerTest controllerTest = new ControllerTest();
		try {
			
			
			controllerTest.testMerEnter();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
