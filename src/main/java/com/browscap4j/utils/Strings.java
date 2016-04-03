package com.browscap4j.utils;

public class Strings {
	public static boolean hasText(final String str){
		if(str==null){
			return false;
		}
		return str.trim().length()>0;
	}
}
