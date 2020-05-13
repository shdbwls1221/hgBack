package com.vtw.pulsar.pss.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

	public static void main(String[] args) {
		String str = "name:aaa,team.id:3";
		Pattern pattern = Pattern.compile("([^:<>,]{0,100})(:|<|>)([^:<>,]{0,100}),");
    	Matcher matcher = pattern.matcher(str + ",");
    	while (matcher.find()) {
    		System.out.println(matcher.group(1));
    		System.out.println(matcher.group(2));
    		System.out.println(matcher.group(3));
        }

	}

}
