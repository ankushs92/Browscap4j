package com.browscap4j.domain;

import org.apache.commons.lang3.BooleanUtils;

public final class BrowserCapabilities {
	private final String browser;
	private final String deviceName;
	private final String deviceType;
	private final String deviceCodeName;
	private final String deviceBrandName;
	private final String platform;
	private final String platformMaker;
	private final boolean isMobile;
	private final boolean isTablet;
	
	public BrowserCapabilities(final Builder builder){
		this.browser = builder.browser;
		this.deviceBrandName = builder.deviceBrandName;
		this.deviceCodeName = builder.deviceCodeName;
		this.deviceName = builder.deviceName;
		this.deviceType = builder.deviceType;
		this.isMobile = builder.isMobile;
		this.isTablet = builder.isTablet;
		this.platform = builder.platform;
		this.platformMaker = builder.platformMaker;
	}

	public static class Builder{
		private  String browser;
		private  String deviceName;
		private  String deviceType;
		private  String deviceCodeName;
		private  String deviceBrandName;
		private  String platform;
		private  String platformMaker;
		private  boolean isMobile;
		private  boolean isTablet;
		
		public Builder browser(final String browser){
			this.browser = browser;
			return this;
		}
		public Builder deviceName(final String deviceName){
			this.deviceName = deviceName;
			return this;
		}
		public Builder deviceType(final String deviceType){
			this.deviceType = deviceType;
			return this;
		}
		public Builder deviceCodeName(final String deviceCodeName){
			this.deviceCodeName =deviceCodeName;
			return this;
		}
		public Builder deviceBrandName(final String deviceBrandName){
			this.deviceBrandName = deviceBrandName;
			return this;
		}
		public Builder platform(final String platform){
			this.platform = platform;
			return this;
		}
		
		public Builder platformMaker(final String platformMaker){
			this.platformMaker = platformMaker;
			return this;
		}
		
		public Builder isMobile(final boolean isMobile){
			this.isMobile = isMobile;
			return this;
		}
		
		public Builder isTablet(final boolean isTablet){
			this.isTablet = isTablet;
			return this;
		}

		public BrowserCapabilities build(){
			return new BrowserCapabilities(this);
		}
	}
	
	public String getBrowser() {
		return browser;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getDeviceCodeName() {
		return deviceCodeName;
	}

	public String getDeviceBrandName() {
		return deviceBrandName;
	}

	public String getPlatform() {
		return platform;
	}

	public String getPlatformMaker() {
		return platformMaker;
	}

	public boolean isMobile() {
		return isMobile;
	}

	public boolean isTablet() {
		return isTablet;
	}

	@Override
	public String toString() {
		return "BrowserCapabilities [browser=" + browser + ", deviceName=" + deviceName + ", deviceType=" + deviceType
				+ ", deviceCodeName=" + deviceCodeName + ", deviceBrandName=" + deviceBrandName + ", platform="
				+ platform + ", platformMaker=" + platformMaker + ", isMobile=" + isMobile + ", isTablet=" + isTablet
				+ "]";
	}

}
