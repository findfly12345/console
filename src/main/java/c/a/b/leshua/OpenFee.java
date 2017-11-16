package c.a.b.leshua;

/**
 * Created by APPLE on 2017/8/30.
 */
public class OpenFee {

    private int openType;

    private String merchantId;

    private ShaoMaFee alipay;

    private ShaoMaFee weixin;

    public int getOpenType() {
        return openType;
    }

    public void setOpenType(int openType) {
        this.openType = openType;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public ShaoMaFee getAlipay() {
        return alipay;
    }

    public void setAlipay(ShaoMaFee alipay) {
        this.alipay = alipay;
    }

    public ShaoMaFee getWeixin() {
        return weixin;
    }

    public void setWeixin(ShaoMaFee weixin) {
        this.weixin = weixin;
    }
}
