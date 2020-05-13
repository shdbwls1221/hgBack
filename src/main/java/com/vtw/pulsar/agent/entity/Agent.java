package com.vtw.pulsar.agent.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="AGENT_TEST")
@SequenceGenerator(
        name="AGENT_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="AGENT_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class Agent {
	
	@Column(name="AGENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENT_SEQ_GEN")
    @Id
	private String agentId;
	
	@Column(name="AGENT_NAME")
	private String agentName;
	
	@Column(name="INST_NAME")
	private String instName;
	
	@Column(name="AGENT_DESC")
	private String agentDesc;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="CREATE_DATE" ,updatable=false)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_DATE")
	@UpdateTimestamp
	private Date updateDate;
	
	@OneToMany(mappedBy="agent",fetch=FetchType.LAZY)
	
	private Set<Route> route;
	
	
	public Set<Route> getRoute() {
		return route;
	}
	public void setRoute(Set<Route> route) {
		this.route = route;
	}

	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentDesc() {
		return agentDesc;
	}
	public void setAgentDesc(String agentDesc) {
		this.agentDesc = agentDesc;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

}
