package com.allcheer.bpos.util;

import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;

import java.io.*;
/**
 * Created by fireWorks on 2016/2/27.
 */
public class ImgUtil {

	 //解码
    public static void generateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
       
        try {
            // Base64解码
            byte[] bytes = Base64Util.decode(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
        	new NotifyException(ErrorRespEnum.RESP009998, "系统错误");
        }
    }
    //编码
    public static String generateImageStr(String imgFilePath) throws IOException {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    	 InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try   
	        {  
	            in = new FileInputStream(imgFilePath);          
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
	        }   
	        catch (IOException e)   
	        {  
	            e.printStackTrace();  
	        }  
	        return Base64Util.encode(data);
    }

}
