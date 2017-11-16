import com.allcheer.bpos.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by pengleilei on 2017/6/8.
 */
public class LocalRegisterHanYin {


    private static final Logger logger = LoggerFactory.getLogger(LocalRegisterHanYin.class);


    public static void main(String[] args) {
        LocalRegisterHanYin register = new LocalRegisterHanYin();
        register.register();
    }

    private void register() {        
		List<String> merList = Arrays.asList
				("201703060002961");
        
        String operFlag = "A";
        try {
            File writename = new File("E://output.txt");
            if (!writename.isFile()) {
                writename.createNewFile(); // 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
    		for(String merId: merList){
    		
                String params = "merId=" + merId + "&operFlag=" + operFlag;
                String json = build(params, "/instMerTX/local/register");
                Map<String, String> map = JsonUtil.toMap(json);
                String errorCode = map.get("statusCode");
                String errorMsg = map.get("statusMsg");

                //将日志读到一个文件里
                out.write(merId + "===" + errorCode + "===" + errorMsg + "\r");    			
    		}
            
            
            
            
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("=========END==========");
    }

    private String build(String params, String service) {

        HttpURLConnection httpUrlConnection = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL("http://140.207.82.189:9971/Bpos" + service);
            //  URL url = new URL("http://localhost:7788" + service);
            URLConnection urlConnection = url.openConnection();
            httpUrlConnection = (HttpURLConnection) urlConnection;

            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setRequestMethod("POST");

            httpUrlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpUrlConnection.setRequestProperty("Charset", "UTF-8");

            httpUrlConnection.connect();

            DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
            dos.writeBytes(params);
            dos.flush();
            dos.close();

            int resultCode = httpUrlConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                String readLine = new String();
                BufferedReader responseReader = new BufferedReader(
                        new InputStreamReader(httpUrlConnection.getInputStream()));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("");
                }
                responseReader.close();

                logger.info("{返回}" + sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }

        return sb.toString();
    }


}
