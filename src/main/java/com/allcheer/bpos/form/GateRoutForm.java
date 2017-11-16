package com.allcheer.bpos.form;

public class GateRoutForm extends BaseForm<GateRoutForm>{
	private String gateId;

    private String lineIp;

    private String lineName;

    private String linePort;

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId == null ? null : gateId.trim();
    }

    public String getLineIp() {
        return lineIp;
    }

    public void setLineIp(String lineIp) {
        this.lineIp = lineIp == null ? null : lineIp.trim();
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getLinePort() {
        return linePort;
    }

    public void setLinePort(String linePort) {
        this.linePort = linePort == null ? null : linePort.trim();
    }
}
