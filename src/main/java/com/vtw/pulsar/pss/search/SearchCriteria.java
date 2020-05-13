package com.vtw.pulsar.pss.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteria {
	
    private String key;
    private String operation;
    private Object value;
    
    public SearchCriteria(String key, String operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public static List<SearchCriteria> getSearches(String search) {
    	List<SearchCriteria> searches = new ArrayList<>();
    	//Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    	Pattern pattern = Pattern.compile("([^:<>,]{0,100})(:|<|>)([^:<>,]{0,100}),");    	
    	Matcher matcher = pattern.matcher(search + ",");
    	while (matcher.find()) {
    		searches.add(new SearchCriteria(matcher.group(1), 
              matcher.group(2), matcher.group(3)));
        }
    	return searches;
    }
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean isLike() {
		return this.operation.equalsIgnoreCase(":");
	}

	public boolean isKey(String key) {
		return this.key.equals(key);
	}

	public String getLikeValue() {
		return "%" + value + "%";
	}

	@Override
	public String toString() {
		return "SearchCriteria [key=" + key + ", operation=" + operation + ", value=" + value + "]";
	}
	
	
}