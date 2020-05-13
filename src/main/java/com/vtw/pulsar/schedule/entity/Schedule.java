package com.vtw.pulsar.schedule.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vtw.pulsar.schedule.tempIntf.ConInterface;

@Entity
@Table(name="SCHEDULE_TRG")
public class Schedule implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="TRG_ID")
    private String trgId;

    @Column(name="TRG_GROUP")
    private String trgGroup;

    @Column(name="CRON")
    private String cron;
    
    @Column(name="NEXT_FIRE_TIME")
    private LocalTime nextFireTime;
    
    @Column(name="PREV_FIRE_TIME")
    private LocalTime prevFireTime;
    
    @Column(name="TRG_STATE")
    private String trgState;
    
    @ManyToOne
    @JoinColumn(name="INTF_ID")
    private ConInterface conInterface;
    
	public Schedule() {	}

	public Schedule(String trgId, String trgGroup) {
		super();
		this.trgId = trgId;
		this.trgGroup = trgGroup;
	}
	
	public Schedule(String trgId, String trgGroup, String intfId) {
		super();
		this.trgId = trgId;
		this.trgGroup = trgGroup;
		this.conInterface = new ConInterface();
		this.conInterface.setIntfId(intfId);
	}

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

	public LocalTime getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(LocalTime nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public LocalTime getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(LocalTime prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public String getTrgState() {
		return trgState;
	}

	public void setTrgState(String trgState) {
		this.trgState = trgState;
	}

	public ConInterface getConInterface() {
		return conInterface;
	}

	public void setConInterface(ConInterface conInterface) {
		this.conInterface = conInterface;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Schedule [trgId=" + trgId + ", trgGroup=" + trgGroup + ", cron=" + cron + ", nextFireTime="
				+ nextFireTime + ", prevFireTime=" + prevFireTime + ", trgState=" + trgState + ", conInterface="
				+ conInterface + "]";
	}

}
