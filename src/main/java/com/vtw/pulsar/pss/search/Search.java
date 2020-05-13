package com.vtw.pulsar.pss.search;

public class Search {
	String kind;
	String label;
	String value;
	String column;
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
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "Search [kind=" + kind + ", label=" + label + ", value=" + value + ", column=" + column + "]";
	}
}
