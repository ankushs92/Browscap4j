package com.browscap4j.utils;

import java.io.File;

import com.browscap4j.domain.Browscap;
import com.browscap4j.domain.BrowserCapabilities;

public class Strings {
	public static boolean hasText(final String str){
		if(str==null){
			return false;
		}
		return str.trim().length()>0;
	}
}
