package com.vtw.pulsar.schedule.tempIntf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCHEDULE_INTF")
public class ConInterface {

    @Id    
    @Column(name="intf_id")
    private String intfId;
    
    @Column(name="intf_name")
    private String intfName;
    
    @Column(name="inst_code")
    private String instCode;

	public ConInterface() {}
    
	public ConInterface(String intfId, String intfName, String instCode) {
		this.intfId = intfId;
		this.intfName = intfName;
		this.instCode = instCode;
	}

	public String getIntfId() {
		return intfId;
	}

	public void setIntfId(String intfId) {
		this.intfId = intfId;
	}

	public String getIntfName() {
		return intfName;
	}

	public void setIntfName(String intfName) {
		this.intfName = intfName;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	@Override
	public String toString() {
		return "ScheduleInterface [intfId=" + intfId + ", intfName=" + intfName + ", instCode=" + instCode + "]";
	}
	
}
