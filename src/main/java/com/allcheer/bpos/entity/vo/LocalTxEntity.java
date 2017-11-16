package com.allcheer.bpos.entity.vo;

/**
 * Created by pengleilei on 2017/5/18.
 */
public class LocalTxEntity {

    private String ord_id;//订单号

    private String machine_no;//商户号

    private String acq_inst_id_code;//机构号

    private String inst_mer_term_id;//终端号


    public String getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(String ord_id) {
        this.ord_id = ord_id;
    }

    public String getMachine_no() {
        return machine_no;
    }

    public void setMachine_no(String machine_no) {
        this.machine_no = machine_no;
    }

    public String getAcq_inst_id_code() {
        return acq_inst_id_code;
    }

    public void setAcq_inst_id_code(String acq_inst_id_code) {
        this.acq_inst_id_code = acq_inst_id_code;
    }

    public String getInst_mer_term_id() {
        return inst_mer_term_id;
    }

    public void setInst_mer_term_id(String inst_mer_term_id) {
        this.inst_mer_term_id = inst_mer_term_id;
    }
}
