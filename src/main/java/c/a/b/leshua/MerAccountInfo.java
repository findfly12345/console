package c.a.b.leshua;

/**
 * Created by APPLE on 2017/8/29.
 */
public class MerAccountInfo {

    private String cardId;//银行卡号

    private String holder;//开户名 个人账户：和基本信息中姓名一致 企业账户：与营业执照注册名称一致

    private String branch;//开户行网点

    private String unionpay;//开户支行联行号，如有，以联行号为准

    private int type;//1：个人账户；2：公司账户

    private String mobile;//银行预留手机号

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUnionpay() {
        return unionpay;
    }

    public void setUnionpay(String unionpay) {
        this.unionpay = unionpay;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
