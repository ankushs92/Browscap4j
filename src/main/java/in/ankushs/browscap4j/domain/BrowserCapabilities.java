package in.ankushs.browscap4j.domain;

import in.ankushs.browscap4j.utils.Strings;
/**
 * Core object that represents the capabilities of an user agent.
 *
 * @author Ankush Sharma
 *
 */

public final class BrowserCapabilities {
	private final String browser;
	private final String deviceName;
	private final String browserType;
	private final String browserMajorVersion;
	private final String deviceType;
	private final String deviceCodeName;
	private final String deviceBrandName;
	private final String platform;
	private final String platformMaker;
	private final String platformVersion;
	private final boolean isMobile;
	private final boolean isTablet;

	public BrowserCapabilities(final Builder builder){
		this.browser = builder.browser;
		this.browserType = builder.browserType;
		this.browserMajorVersion = builder.browserMajorVersion;
		this.deviceBrandName = builder.deviceBrandName;
		this.deviceCodeName = builder.deviceCodeName;
		this.deviceName = builder.deviceName;
		this.deviceType = builder.deviceType;
		this.isMobile = builder.isMobile;
		this.isTablet = builder.isTablet;
		this.platform = builder.platform;
		this.platformMaker = builder.platformMaker;
		this.platformVersion = builder.platformVersion;
	}

   public static class Builder{
		private  String browser;
	    private  String browserType;
	    private  String browserMajorVersion;
		private  String deviceName;
		private  String deviceType;
		private  String deviceCodeName;
		private  String deviceBrandName;
		private  String platform;
		private  String platformMaker;
		private  String platformVersion;
		private  boolean isMobile;
		private  boolean isTablet;

	    public Builder browser(final String browser){
			this.browser = browser;
			return this;
		}

	    public Builder browserType(final String browserType){
		   this.browserType = browserType;
		   return this;
	    }

	    public Builder browserMajorVersion(final String browserMajorVersion){
		   this.browserMajorVersion = browserMajorVersion;
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
		public Builder platformVersion(final String platformVersion){
			this.platformVersion = platformVersion;
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

	public String getBrowserType() {return browserType;}

	public String getBrowserMajorVersion() {
		return browserMajorVersion;
	}

	public boolean isMobile() {
		return isMobile;
	}

	public String getPlatformVersion() {
		return platformVersion;
	}

	public boolean isTablet() {
		return isTablet;
	}

	public boolean isDesktop(){
		final String deviceType = getDeviceType();
		if(!Strings.hasText(deviceType)){
			return false;
		}
		if(deviceType.equals("Desktop")){
			return true;
		}
		return false;
	}
	public boolean isBot(){
		return getBrowserType().equals("Bot/Crawler");
	}

	public boolean isiOS(){
		return getPlatform().equals("iOS");
	}

	public boolean isAndroid(){
		return getPlatform().equals("Android");
	}

	public boolean isWindows(){
		//Sometimes the platform is Win8Phone or Win7Phone.
		//This is rather a crude way of doing it and is done on the basis of observation of how browscap resolves windows user agents.
		return getPlatform().contains("Win");
	}

	@Override
	public String toString() {
		return "BrowserCapabilities{" +
				"browser='" + browser + '\'' +
				", deviceName='" + deviceName + '\'' +
				", browserType='" + browserType + '\'' +
				", browserMajorVersion='" + browserMajorVersion + '\'' +
				", deviceType='" + deviceType + '\'' +
				", deviceCodeName='" + deviceCodeName + '\'' +
				", deviceBrandName='" + deviceBrandName + '\'' +
				", platform='" + platform + '\'' +
				", platformMaker='" + platformMaker + '\'' +
				", platformVersion='" + platformVersion + '\'' +
				", isMobile=" + isMobile +
				", isTablet=" + isTablet +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BrowserCapabilities that = (BrowserCapabilities) o;

		if (isMobile != that.isMobile) return false;
		if (isTablet != that.isTablet) return false;
		if (!browser.equals(that.browser)) return false;
		if (!deviceName.equals(that.deviceName)) return false;
		if (!browserType.equals(that.browserType)) return false;
		if (!deviceType.equals(that.deviceType)) return false;
		if (!deviceCodeName.equals(that.deviceCodeName)) return false;
		if (!deviceBrandName.equals(that.deviceBrandName)) return false;
		if (!platform.equals(that.platform)) return false;
		if (!platformMaker.equals(that.platformMaker)) return false;
		return platformVersion.equals(that.platformVersion);

	}

	@Override
	public int hashCode() {
		int result = browser.hashCode();
		result = 31 * result + deviceName.hashCode();
		result = 31 * result + browserType.hashCode();
		result = 31 * result + deviceType.hashCode();
		result = 31 * result + deviceCodeName.hashCode();
		result = 31 * result + deviceBrandName.hashCode();
		result = 31 * result + platform.hashCode();
		result = 31 * result + platformMaker.hashCode();
		result = 31 * result + platformVersion.hashCode();
		result = 31 * result + (isMobile ? 1 : 0);
		result = 31 * result + (isTablet ? 1 : 0);
		return result;
	}
}
