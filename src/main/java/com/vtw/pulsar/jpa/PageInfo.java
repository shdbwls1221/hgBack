package com.vtw.pulsar.jpa;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageInfo {
	
	private int pNo;
	private int pSize;
	private String dir;
	private String key;
	
	public PageInfo() {}

	public PageInfo(int pNo, int pSize, String dir, String key) {
		this.pNo = pNo;
		this.pSize = pSize;
		this.dir = dir;
		this.key = key;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getpSize() {
		return pSize;
	}

	public void setpSize(int pSize) {
		this.pSize = pSize;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "PageInfo [pNo=" + pNo + ", pSize=" + pSize + ", key=" + key + ", dir=" + dir + "]";
	}
	
	public Pageable toPageable(String defaultKey) {

    	int pNo = this.pNo;
    	int pSize = this.pSize;
    	Sort.Direction direction = this.dir.equals("desc") ? Direction.DESC : Direction.ASC;
    	String key = (this.key.equals("defaultKey")) ? defaultKey : this.key;
    	
    	return PageRequest.of(pNo, pSize, new Sort(direction, key));
	}
	
	
}
