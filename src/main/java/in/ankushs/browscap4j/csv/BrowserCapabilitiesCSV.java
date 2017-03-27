package in.ankushs.browscap4j.csv;

import com.opencsv.bean.CsvBindByName;

public class BrowserCapabilitiesCSV {

    @CsvBindByName(column = "PropertyName")
    private String propertyName;
    @CsvBindByName(column = "MasterParent")
    private String masterParent;
    @CsvBindByName(column = "LiteMode")
    private String liteMode;
    @CsvBindByName(column = "Parent")
    private String parent;
    @CsvBindByName(column = "Comment")
    private String comment;
    @CsvBindByName(column = "Browser")
    private String browser;
    @CsvBindByName(column = "Browser_Type")
    private String browserType;
    @CsvBindByName(column = "Browser_Bits")
    private String browserBits;
    @CsvBindByName(column = "Browser_Maker")
    private String browserMaker;
    @CsvBindByName(column = "Browser_Modus")
    private String browserModus;
    @CsvBindByName(column = "Version")
    private String version;
    @CsvBindByName(column = "MajorVer")
    private String majorVer;
    @CsvBindByName(column = "MinorVer")
    private String minorVer;
    @CsvBindByName(column = "Platform")
    private String platform;
    @CsvBindByName(column = "Platform_Version")
    private String platformVersion;
    @CsvBindByName(column = "Platform_Description")
    private String platformDescription;
    @CsvBindByName(column = "Platform_Bits")
    private String platformBits;
    @CsvBindByName(column = "Platform_Maker")
    private String platformMaker;
    @CsvBindByName(column = "Alpha")
    private String alpha;
    @CsvBindByName(column = "Beta")
    private String beta;
    @CsvBindByName(column = "Win16")
    private String win16;
    @CsvBindByName(column = "Win32")
    private String win32;
    @CsvBindByName(column = "Win64")
    private String win64;
    @CsvBindByName(column = "Frames")
    private String frames;
    @CsvBindByName(column = "IFrames")
    private String iFrames;
    @CsvBindByName(column = "Tables")
    private String tables;
    @CsvBindByName(column = "Cookies")
    private String cookies;
    @CsvBindByName(column = "BackgroundSounds")
    private String backgroundSounds;
    @CsvBindByName(column = "JavaScript")
    private String javaScript;
    @CsvBindByName(column = "VBScript")
    private String vBScript;
    @CsvBindByName(column = "JavaApplets")
    private String javaApplets;
    @CsvBindByName(column = "ActiveXControls")
    private String activeXControls;
    @CsvBindByName(column = "isMobileDevice")
    private boolean mobileDevice;
    @CsvBindByName(column = "isTablet")
    private boolean tablet;
    @CsvBindByName(column = "isSyndicationReader")
    private boolean syndicationReader;
    @CsvBindByName(column = "Crawler")
    private String crawler;
    @CsvBindByName(column = "isFake")
    private boolean fake;
    @CsvBindByName(column = "isAnonymized")
    private boolean anonymized;
    @CsvBindByName(column = "isModified")
    private boolean modified;
    @CsvBindByName(column = "CssVersion")
    private String cssVersion;
    @CsvBindByName(column = "AolVersion")
    private String aolVersion;
    @CsvBindByName(column = "Device_Name")
    private String deviceName;
    @CsvBindByName(column = "Device_Maker")
    private String deviceMaker;
    @CsvBindByName(column = "Device_Type")
    private String deviceType;
    @CsvBindByName(column = "Device_Pointing_Method")
    private String devicePointingMethod;
    @CsvBindByName(column = "Device_Code_Name")
    private String deviceCodeName;
    @CsvBindByName(column = "Device_Brand_Name")
    private String deviceBrandName;
    @CsvBindByName(column = "RenderingEngine_Name")
    private String renderingEngineName;
    @CsvBindByName(column = "RenderingEngine_Version")
    private String renderingEngineVersion;
    @CsvBindByName(column = "RenderingEngine_Description")
    private String renderingEngineDescription;
    @CsvBindByName(column = "RenderingEngine_Maker")
    private String renderingEngineMaker;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getMasterParent() {
        return masterParent;
    }

    public void setMasterParent(String masterParent) {
        this.masterParent = masterParent;
    }

    public String getLiteMode() {
        return liteMode;
    }

    public void setLiteMode(String liteMode) {
        this.liteMode = liteMode;
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

    public String getBrowserBits() {
        return browserBits;
    }

    public void setBrowserBits(String browserBits) {
        this.browserBits = browserBits;
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

    public String getPlatformBits() {
        return platformBits;
    }

    public void setPlatformBits(String platformBits) {
        this.platformBits = platformBits;
    }

    public String getPlatformMaker() {
        return platformMaker;
    }

    public void setPlatformMaker(String platformMaker) {
        this.platformMaker = platformMaker;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getWin16() {
        return win16;
    }

    public void setWin16(String win16) {
        this.win16 = win16;
    }

    public String getWin32() {
        return win32;
    }

    public void setWin32(String win32) {
        this.win32 = win32;
    }

    public String getWin64() {
        return win64;
    }

    public void setWin64(String win64) {
        this.win64 = win64;
    }

    public String getFrames() {
        return frames;
    }

    public void setFrames(String frames) {
        this.frames = frames;
    }

    public String getiFrames() {
        return iFrames;
    }

    public void setiFrames(String iFrames) {
        this.iFrames = iFrames;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getBackgroundSounds() {
        return backgroundSounds;
    }

    public void setBackgroundSounds(String backgroundSounds) {
        this.backgroundSounds = backgroundSounds;
    }

    public String getJavaScript() {
        return javaScript;
    }

    public void setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }

    public String getvBScript() {
        return vBScript;
    }

    public void setvBScript(String vBScript) {
        this.vBScript = vBScript;
    }

    public String getJavaApplets() {
        return javaApplets;
    }

    public void setJavaApplets(String javaApplets) {
        this.javaApplets = javaApplets;
    }

    public String getActiveXControls() {
        return activeXControls;
    }

    public void setActiveXControls(String activeXControls) {
        this.activeXControls = activeXControls;
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

    public String getCrawler() {
        return crawler;
    }

    public void setCrawler(String crawler) {
        this.crawler = crawler;
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

}
