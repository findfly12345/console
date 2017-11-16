package c.a.b.leshua;

/**
 * Created by APPLE on 2017/8/29.
 */
public class BaseReq {

    private String agentId;//代理商编号，由乐刷分配的接入方唯一标识，明文传输。测试环境固定为10。

    private String version;//目前固定值1.0

    private String reqSerialNo;//请求流水号(全局唯一，建议为 yyyymmddXXXXXXXX，其中 XXXXXXXX 为 8 位顺序,目前只作为定位请求用)

    private String sign;//签名串，业务数据明文参与签名后经 Base64 编码的字符串。

    private String data;//业务数据明文字符串（Json格式，参照各个接口中输入参数说明.

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
