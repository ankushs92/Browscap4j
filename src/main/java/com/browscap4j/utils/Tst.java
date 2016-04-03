package com.browscap4j.utils;

import java.io.File;

import com.browscap4j.domain.Browscap;
import com.browscap4j.domain.BrowserCapabilities;

public class Tst {
	public static void main(String[] args) throws InterruptedException {
		Browscap browscap = new Browscap(new File("/Users/Ankush/Downloads/browscap/browscap.csv"));
		String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36";
		long start = System.currentTimeMillis(); 
		BrowserCapabilities browserCapabilities = browscap.lookup(userAgent);
		long stop = System.currentTimeMillis();
		System.out.println(browserCapabilities);
		System.out.println(stop-start);
		Thread.sleep(1000);
			long mainStart = System.currentTimeMillis();
		 start = System.currentTimeMillis(); 
		 
		 browserCapabilities = browscap.lookup(userAgent);
		 stop = System.currentTimeMillis();
			long mainStop = System.currentTimeMillis();

		 System.out.println(stop-start);
		 browscap = new Browscap(new File("/Users/Ankush/Downloads/browscap/browscap.csv"));
		 System.out.println(mainStop-mainStart);
		 
	}
}
