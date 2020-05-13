package com.vtw.pulsar.user.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserSearch {
	
	private long id = 0;
	private String name = "";
	private String startDate = "1970-01-01";
	private String endDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	private long teamId = 0;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	@Override
	public String toString() {
		return "UserSearch [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", teamId=" + teamId + "]";
	}
	
}
