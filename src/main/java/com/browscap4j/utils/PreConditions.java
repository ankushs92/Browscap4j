package com.browscap4j.utils;

public class PreConditions {
	public static <T> void checkNull(final T  t , final String errorMsg){
		if(t==null){
			throw new IllegalArgumentException(errorMsg);
		}
	}
	
	public static  void checkEmptyString(final String str , final String errorMsg){
		if(!Strings.hasText(str)){
			throw new IllegalArgumentException(errorMsg);
		}
	}
	
	public static  void checkExpression(final boolean expression , final String errorMsg){
		if(expression){
			throw new IllegalArgumentException(errorMsg);
		}
	}
}
