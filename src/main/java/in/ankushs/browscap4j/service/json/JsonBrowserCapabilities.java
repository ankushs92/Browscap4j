package in.ankushs.browscap4j.service.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonBrowserCapabilities {

    @JsonProperty("PropertyName")
    private String propertyName;

    @JsonProperty("MasterParent")
    private boolean masterParent;

    @JsonProperty("LiteMode")
    private boolean liteMode;

    @JsonProperty("Parent")
    private String parent;

    @JsonProperty("Comment")
    private String comment;

    @JsonProperty("Browser")
    private String browser;

    @JsonProperty("Browser_Type")
    private String browserType;

    @JsonProperty("Browser_Bits")
    private int browserBits;

    @JsonProperty("Browser_Maker")
    private String browserMaker;

    @JsonProperty("Browser_Modus")
    private String browserModus;

    @JsonProperty("Version")
    private String version;

    @JsonProperty("MajorVer")
    private String majorVer;

    @JsonProperty("MinorVer")
    private String minorVer;

    @JsonProperty("Platform")
    private String platform;

    @JsonProperty("Platform_Version")
    private String platformVersion;

    @JsonProperty("Platform_Description")
    private String platformDescription;

    @JsonProperty("Platform_Bits")
    private double platformBits;

    @JsonProperty("Platform_Maker")
    private String platformMaker;

    @JsonProperty("Alpha")
    private boolean alpha;

    @JsonProperty("Beta")
    private boolean beta;

    @JsonProperty("Win16")
    private boolean win16;

    @JsonProperty("Win32")
    private boolean win32;

    @JsonProperty("Win64")
    private boolean win64;

    @JsonProperty("Frames")
    private boolean frames;

    @JsonProperty("IFrames")
    private boolean iFrames;

    @JsonProperty("Tables")
    private boolean tables;

    @JsonProperty("Cookies")
    private boolean cookies;

    @JsonProperty("BackgroundSounds")
    private boolean backgroundSounds;

    @JsonProperty("JavaScript")
    private boolean javaScript;

    @JsonProperty("VBScript")
    private boolean vBScript;

    @JsonProperty("JavaApplets")
    private boolean javaApplets;

    @JsonProperty("ActiveXControls")
    private boolean activeXControls;

    @JsonProperty("isMobileDevice")
    private boolean mobileDevice;

    @JsonProperty("isTablet")
    private boolean tablet;

    @JsonProperty("isSyndicationReader")
    private boolean syndicationReader;

    @JsonProperty("Crawler")
    private boolean crawler;

    @JsonProperty("isFake")
    private boolean fake;

    @JsonProperty("isAnonymized")
    private boolean anonymized;

    @JsonProperty("isModified")
    private boolean modified;

    @JsonProperty("CssVersion")
    private String cssVersion;

    @JsonProperty("AolVersion")
    private String aolVersion;

    @JsonProperty("Device_Name")
    private String deviceName;

    @JsonProperty("Device_Maker")
    private String deviceMaker;

    @JsonProperty("Device_Type")
    private String deviceType;

    @JsonProperty("Device_Pointing_Method")
    private String devicePointingMethod;

    @JsonProperty("Device_Code_Name")
    private String deviceCodeName;

    @JsonProperty("Device_Brand_Name")
    private String deviceBrandName;

    @JsonProperty("RenderingEngine_Name")
    private String renderingEngineName;

    @JsonProperty("RenderingEngine_Version")
    private String renderingEngineVersion;

    @JsonProperty("RenderingEngine_Description")
    private String renderingEngineDescription;

    @JsonProperty("RenderingEngine_Maker")
    private String renderingEngineMaker;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean getMasterParent() {
        return masterParent;
    }

    public void setMasterParent(String masterParent) {
        this.masterParent = Boolean.parseBoolean(masterParent);
    }

    public boolean getLiteMode() {
        return liteMode;
    }

    public void setLiteMode(String liteMode) {
        this.liteMode = Boolean.parseBoolean(liteMode);
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public int getBrowserBits() {
        return browserBits;
    }

    public void setBrowserBits(String browserBits) {
        this.browserBits = Integer.parseInt(browserBits);
    }

    public String getBrowserMaker() {
        return browserMaker;
    }

    public void setBrowserMaker(String browserMaker) {
        this.browserMaker = browserMaker;
    }

    public String getBrowserModus() {
        return browserModus;
    }

    public void setBrowserModus(String browserModus) {
        this.browserModus = browserModus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMajorVer() {
        return majorVer;
    }

    public void setMajorVer(String majorVer) {
        this.majorVer = majorVer;
    }

    public String getMinorVer() {
        return minorVer;
    }

    public void setMinorVer(String minorVer) {
        this.minorVer = minorVer;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getPlatformDescription() {
        return platformDescription;
    }

    public void setPlatformDescription(String platformDescription) {
        this.platformDescription = platformDescription;
    }

    public double getPlatformBits() {
        return platformBits;
    }

    public void setPlatformBits(String platformBits) {
        this.platformBits = Double.parseDouble(platformBits);
    }

    public String getPlatformMaker() {
        return platformMaker;
    }

    public void setPlatformMaker(String platformMaker) {
        this.platformMaker = platformMaker;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = Boolean.parseBoolean(alpha);
    }

    public boolean isBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = Boolean.parseBoolean(beta);
    }

    public boolean getWin16() {
        return win16;
    }

    public void setWin16(String win16) {
        this.win16 = Boolean.parseBoolean(win16);
    }

    public boolean getWin32() {
        return win32;
    }

    public void setWin32(String win32) {
        this.win32 = Boolean.parseBoolean(win32);
    }

    public boolean getWin64() {
        return win64;
    }

    public void setWin64(String win64) {
        this.win64 = Boolean.parseBoolean(win64);
    }

    public boolean getFrames() {
        return frames;
    }

    public void setFrames(String frames) {
        this.frames = Boolean.parseBoolean(frames);
    }

    public boolean getiFrames() {
        return iFrames;
    }

    public void setiFrames(String iFrames) {
        this.iFrames = Boolean.parseBoolean(iFrames);
    }

    public boolean getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = Boolean.parseBoolean(tables);
    }

    public boolean getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = Boolean.parseBoolean(cookies);
    }

    public boolean getBackgroundSounds() {
        return backgroundSounds;
    }

    public void setBackgroundSounds(String backgroundSounds) {
        this.backgroundSounds = Boolean.parseBoolean(backgroundSounds);
    }

    public boolean getJavaScript() {
        return javaScript;
    }

    public void setJavaScript(String javaScript) {
        this.javaScript = Boolean.parseBoolean(javaScript);
    }

    public boolean getvBScript() {
        return vBScript;
    }

    public void setvBScript(String vBScript) {
        this.vBScript = Boolean.parseBoolean(vBScript);
    }

    public boolean getJavaApplets() {
        return javaApplets;
    }

    public void setJavaApplets(String javaApplets) {
        this.javaApplets = Boolean.parseBoolean(javaApplets);
    }

    public boolean getActiveXControls() {
        return activeXControls;
    }

    public void setActiveXControls(String activeXControls) {
        this.activeXControls = Boolean.parseBoolean(activeXControls);
    }

    public boolean isMobileDevice() {
        return mobileDevice;
    }

    public void setMobileDevice(boolean mobileDevice) {
        this.mobileDevice = mobileDevice;
    }

    public void setMobileDevice(String mobileDevice) {
        this.mobileDevice = Boolean.getBoolean(mobileDevice);
    }

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet) {
        this.tablet = tablet;
    }

    public void setTablet(String tablet) {
        this.tablet = Boolean.parseBoolean(tablet);
    }

    public boolean isSyndicationReader() {
        return syndicationReader;
    }

    public void setSyndicationReader(boolean syndicationReader) {
        this.syndicationReader = syndicationReader;
    }

    public void setSyndicationReader(String syndicationReader) {
        this.syndicationReader = Boolean.parseBoolean(syndicationReader);
    }

    public boolean getCrawler() {
        return crawler;
    }

    public void setCrawler(String crawler) {
        this.crawler = Boolean.parseBoolean(crawler);
    }

    public boolean isFake() {
        return fake;
    }

    public void setFake(boolean fake) {
        this.fake = fake;
    }

    public void setFake(String fake) {
        this.fake = Boolean.parseBoolean(fake);
    }

    public boolean isAnonymized() {
        return anonymized;
    }

    public void setAnonymized(boolean anonymized) {
        this.anonymized = anonymized;
    }

    public void setAnonymized(String anonymized) {
        this.anonymized = Boolean.parseBoolean(anonymized);
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public void setModified(String modified) {
        this.modified = Boolean.parseBoolean(modified);
    }

    public String getCssVersion() {
        return cssVersion;
    }

    public void setCssVersion(String cssVersion) {
        this.cssVersion = cssVersion;
    }

    public String getAolVersion() {
        return aolVersion;
    }

    public void setAolVersion(String aolVersion) {
        this.aolVersion = aolVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMaker() {
        return deviceMaker;
    }

    public void setDeviceMaker(String deviceMaker) {
        this.deviceMaker = deviceMaker;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevicePointingMethod() {
        return devicePointingMethod;
    }

    public void setDevicePointingMethod(String devicePointingMethod) {
        this.devicePointingMethod = devicePointingMethod;
    }

    public String getDeviceCodeName() {
        return deviceCodeName;
    }

    public void setDeviceCodeName(String deviceCodeName) {
        this.deviceCodeName = deviceCodeName;
    }

    public String getDeviceBrandName() {
        return deviceBrandName;
    }

    public void setDeviceBrandName(String deviceBrandName) {
        this.deviceBrandName = deviceBrandName;
    }

    public String getRenderingEngineName() {
        return renderingEngineName;
    }

    public void setRenderingEngineName(String renderingEngineName) {
        this.renderingEngineName = renderingEngineName;
    }

    public String getRenderingEngineVersion() {
        return renderingEngineVersion;
    }

    public void setRenderingEngineVersion(String renderingEngineVersion) {
        this.renderingEngineVersion = renderingEngineVersion;
    }

    public String getRenderingEngineDescription() {
        return renderingEngineDescription;
    }

    public void setRenderingEngineDescription(String renderingEngineDescription) {
        this.renderingEngineDescription = renderingEngineDescription;
    }

    public String getRenderingEngineMaker() {
        return renderingEngineMaker;
    }

    public void setRenderingEngineMaker(String renderingEngineMaker) {
        this.renderingEngineMaker = renderingEngineMaker;
    }

    @Override
    public String toString() {
        return "JsonBrowserCapabilities [propertyName=" + propertyName + ", masterParent=" + masterParent
                + ", liteMode=" + liteMode + ", parent=" + parent + ", comment=" + comment + ", browser=" + browser
                + ", browserType=" + browserType + ", browserBits=" + browserBits + ", browserMaker=" + browserMaker
                + ", browserModus=" + browserModus + ", version=" + version + ", majorVer=" + majorVer + ", minorVer="
                + minorVer + ", platform=" + platform + ", platformVersion=" + platformVersion
                + ", platformDescription=" + platformDescription + ", platformBits=" + platformBits + ", platformMaker="
                + platformMaker + ", alpha=" + alpha + ", beta=" + beta + ", win16=" + win16 + ", win32=" + win32
                + ", win64=" + win64 + ", frames=" + frames + ", iFrames=" + iFrames + ", tables=" + tables
                + ", cookies=" + cookies + ", backgroundSounds=" + backgroundSounds + ", javaScript=" + javaScript
                + ", vBScript=" + vBScript + ", javaApplets=" + javaApplets + ", activeXControls=" + activeXControls
                + ", mobileDevice=" + mobileDevice + ", tablet=" + tablet + ", syndicationReader=" + syndicationReader
                + ", crawler=" + crawler + ", fake=" + fake + ", anonymized=" + anonymized + ", modified=" + modified
                + ", cssVersion=" + cssVersion + ", aolVersion=" + aolVersion + ", deviceName=" + deviceName
                + ", deviceMaker=" + deviceMaker + ", deviceType=" + deviceType + ", devicePointingMethod="
                + devicePointingMethod + ", deviceCodeName=" + deviceCodeName + ", deviceBrandName=" + deviceBrandName
                + ", renderingEngineName=" + renderingEngineName + ", renderingEngineVersion=" + renderingEngineVersion
                + ", renderingEngineDescription=" + renderingEngineDescription + ", renderingEngineMaker="
                + renderingEngineMaker + "]";
    }

}
