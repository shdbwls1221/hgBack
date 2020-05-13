package com.vtw.pulsar.schedule.entity;

public class ScheduleSearch {
	
    private String trgId = "";
    private String trgGroup = "";
    private String cron = "";

    private String intfId = "";

	public String getTrgId() {
		return trgId;
	}

	public void setTrgId(String trgId) {
		this.trgId = trgId;
	}

	public String getTrgGroup() {
		return trgGroup;
	}

	public void setTrgGroup(String trgGroup) {
		this.trgGroup = trgGroup;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getIntfId() {
		return intfId;
	}

	public void setIntfId(String intfId) {
		this.intfId = intfId;
	}

}
