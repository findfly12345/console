package c.a.b.leshua;

/**
 * Created by APPLE on 2017/8/29.
 */
public class MerBaseInfo {
    private String area;// 商户所在地区县(参照附录区县名称规范)

    private String address;//商户详细地址(5-40个字符)

    private String name;//姓名(2-6个汉字) merchantType=1、个人身份证姓名 merchantType=3、企业法人姓名

    private int merchantType;// 1:个人(身份证唯一) 2.个体(已废弃) 3:企业(营业执照唯一)

    private String province;//商户所在地省份(参照附录省份名称规范)

    private String merchantName;//商户简称 个人：商户名称如深圳市贵人鸟鞋服店（城市+名称+行业）或身份证姓名 企业：商户名称如深圳市贵人鸟鞋服店（城市+名称+行业// ）

    private String idcard;//身份证号码

    private String city;//商户所在地城市(参照附录城市名称规范)

    private String mobile;//手机号码（11位手机号）


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(int merchantType) {
        this.merchantType = merchantType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
