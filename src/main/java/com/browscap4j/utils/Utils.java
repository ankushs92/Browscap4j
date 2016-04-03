package com.browscap4j.utils;

public class Utils {
	public static Integer computeLength(final String str){
		if(!Strings.hasText(str)){
			return 0;
		}
		return str.length();
	}
}
