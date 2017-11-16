

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allcheer.bpos.service.AddressService;
import com.allcheer.bpos.service.WechatRegisterService;

/**
 * Created by fireWorks on 2016/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml", "classpath*:refmobile-servlet.xml" })
public class BposMerTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	WechatRegisterService wechatRegisterService;
	
	@Autowired
	AddressService addressService;
	
	/**
	 * 民生商户报备
	 * @throws Exception
	 */
/*	 
	@Test
	@Rollback(false)
	public void doMinShengMerReg() throws Exception {
						
		Map<String, String> registResult1 = wechatRegisterService.registerForMSBank("201703240003097", "UB");
			
	}
*/
	/**
	 * 地址代码测试
	 * @throws Exception
	 */
	@Test
	@Rollback(false)
	public void doAddressTest() throws Exception {
						
		addressService.getCorrectAddressCodes("吉林省", "长春市", "博斯坦乡");
			
	}	
	
    	
	/**
	 * 民生商户报备 - 手动填写
	 * @throws Exception
	 */
	/* 
	@Test
	@Rollback(false)
	public void doMinShengMerRegManual() throws Exception {
				
		MinShengDoRegister minShengDoRegister = new MinShengDoRegister();
		
		minShengDoRegister.setPayWay("ZFBZF");
		minShengDoRegister.setMerchantId("20170526005275304");
		minShengDoRegister.setMerchantName("个体户王敏");
		minShengDoRegister.setShortName("王敏奶茶");
		minShengDoRegister.setMerchantAddress("上海市闵行区虹梅南路2975号");
		
		minShengDoRegister.setProvinceCode("310000");
		minShengDoRegister.setCityCode("310100");
		minShengDoRegister.setDistrictCode("310112");
		minShengDoRegister.setServicePhone("15821135248");
		
		minShengDoRegister.setContactName("王敏");
		minShengDoRegister.setContactType("02");		
		//minShengDoRegister.setCategory("292");   //WX
		minShengDoRegister.setCategory("2015050700000000");   //ZFB
		minShengDoRegister.setIdCard("310104198901040836");
		//minShengDoRegister.setMerchantLicense("92441900MA4W23NC7Y");
		minShengDoRegister.setAccNo("6212261001012589294");
		minShengDoRegister.setAccName("王敏");
		minShengDoRegister.setBankType("102290024433");
		minShengDoRegister.setBankName("中国工商银行上海市分行营业部");
		minShengDoRegister.setT1drawFee("0.2");
		minShengDoRegister.setT1tradeRate("0.0025");
		minShengDoRegister.setT0drawFee("0.2");
		minShengDoRegister.setT0tradeRate("0.0025");		
		
		Map<String, String> registResult = merServiceForMS.merRegisterToMinShengManual(minShengDoRegister);
		System.out.printf("注册结果" + registResult.toString());
	}
    */
	/**
	 * 民生公众号报备
	 * @throws Exception
	 */
	 
	@Test
	@Rollback(false)
	public void doMinShengPubReg() throws Exception {

		//merServiceForMS.msWXPublicRegist("201705240052615", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052615", "WECHAT_PUB_2");

		//merServiceForMS.msWXPublicRegist("201705240052616", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052616", "WECHAT_PUB_2");
		
		//merServiceForMS.msWXPublicRegist("201705240052617", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052617", "WECHAT_PUB_2");

		//merServiceForMS.msWXPublicRegist("201705240052618", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052618", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705240052619", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052619", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705240052620", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052620", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705240052701", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705240052701", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705120052541", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705120052541", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705250052731", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705250052731", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705250052732", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705250052732", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705250052733", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705250052733", "WECHAT_PUB_2");
	
		//merServiceForMS.msWXPublicRegist("201705250052734", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705250052734", "WECHAT_PUB_2");	

		//merServiceForMS.msWXPublicRegist("201705170052546", "WECHAT_PUB_1");
		//merServiceForMS.msWXPublicRegist("201705170052546", "WECHAT_PUB_2");
		
	}
	 	
}
