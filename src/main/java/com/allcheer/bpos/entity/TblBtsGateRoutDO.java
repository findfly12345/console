package com.allcheer.bpos.entity;

public class TblBtsGateRoutDO {
    private String gateId;

    private String lineIp;

    private Integer linePort;

    private String lineName;

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

    public Integer getLinePort() {
        return linePort;
    }

    public void setLinePort(Integer linePort) {
        this.linePort = linePort;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }
}