package com.browscap4j.lookup

import java.io.File;

import com.browscap4j.domain.Browscap;
import com.browscap4j.domain.BrowserCapabilities;

import spock.lang.Specification

class BaseSpec extends Specification{
	static main(Args){
		Browscap browscap = new Browscap(new File("/Users/Ankush/Downloads/browscap/browscap.csv"));
		String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
		long start = System.currentTimeMillis();
		BrowserCapabilities browserCapabilities = browscap.lookup(userAgent);
		long stop = System.currentTimeMillis()
		
		browserCapabilities = browscap.lookup(userAgent);
		browserCapabilities = browscap.lookup(userAgent);
		browserCapabilities = browscap.lookup(userAgent);
		browserCapabilities = browscap.lookup(userAgent);
		browserCapabilities = browscap.lookup(userAgent);
		browserCapabilities = browscap.lookup(userAgent);
		println stop-start
	}
}
