package com.vtw.pulsar.team.entity;

public class TeamSearch {
	
	private String value;
	private String column;
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}

	private long id = 0;
	private String name = "";
	
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
	@Override
	public String toString() {
		return "TeamSearch [value=" + value + ", column=" + column + ", id=" + id + ", name=" + name + "]";
	}
	
	

}
