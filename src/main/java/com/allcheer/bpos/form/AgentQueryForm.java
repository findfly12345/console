package com.allcheer.bpos.form;

import java.util.List;

/**
 * Created by LiuBin on 2017/1/18.
 */
public class AgentQueryForm extends BaseForm{

    private String agentShortName;

    private String memberId;

    private String agentLevel;

    private String memberStat;

    private String showOemSelect;

    private List<String> ids;

    public String getAgentShortName() {
        return agentShortName;
    }

    public void setAgentShortName(String agentShortName) {
        this.agentShortName = agentShortName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getMemberStat() {
        return memberStat;
    }

    public void setMemberStat(String memberStat) {
        this.memberStat = memberStat;
    }

    public String getShowOemSelect() {
        return showOemSelect;
    }

    public void setShowOemSelect(String showOemSelect) {
        this.showOemSelect = showOemSelect;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
