package in.ankushs.browscap4j.service.csv;

import com.opencsv.bean.CsvBindByName;

public class CsvBrowserCapabilities {

    @CsvBindByName(column = "PropertyName")
    private String propertyName;

    @CsvBindByName(column = "MasterParent")
    private boolean masterParent;

//    @CsvBindByName(column = "LiteMode")
    private boolean liteMode;

    @CsvBindByName(column = "Parent")
    private String parent;

    @CsvBindByName(column = "Comment")
    private String comment;

    @CsvBindByName(column = "Browser")
    private String browser;

    @CsvBindByName(column = "Browser_Type")
    private String browserType;

//    @CsvBindByName(column = "Browser_Bits")
    private int browserBits;

    @CsvBindByName(column = "Browser_Maker")
    private String browserMaker;

//    @CsvBindByName(column = "Browser_Modus")
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

//    @CsvBindByName(column = "Platform_Bits")
    private double platformBits;

//    @CsvBindByName(column = "Platform_Maker")
    private String platformMaker;

//    @CsvBindByName(column = "Alpha")
    private boolean alpha;

//    @CsvBindByName(column = "Beta")
    private boolean beta;

//    @CsvBindByName(column = "Win16")
    private boolean win16;

//    @CsvBindByName(column = "Win32")
    private boolean win32;

//    @CsvBindByName(column = "Win64")
    private boolean win64;

//    @CsvBindByName(column = "Frames")
    private boolean frames;

//    @CsvBindByName(column = "IFrames")
    private boolean iFrames;

//    @CsvBindByName(column = "Tables")
    private boolean tables;

//    @CsvBindByName(column = "Cookies")
    private boolean cookies;

//    @CsvBindByName(column = "BackgroundSounds")
    private boolean backgroundSounds;

//    @CsvBindByName(column = "JavaScript")
    private boolean javaScript;

//    @CsvBindByName(column = "VBScript")
    private boolean vBScript;

//    @CsvBindByName(column = "JavaApplets")
    private boolean javaApplets;

//    @CsvBindByName(column = "ActiveXControls")
    private boolean activeXControls;

    @CsvBindByName(column = "isMobileDevice")
    private boolean mobileDevice;

    @CsvBindByName(column = "isTablet")
    private boolean tablet;

//    @CsvBindByName(column = "isSyndicationReader")
    private boolean syndicationReader;

//    @CsvBindByName(column = "Crawler")
    private boolean crawler;

//    @CsvBindByName(column = "isFake")
    private boolean fake;

//    @CsvBindByName(column = "isAnonymized")
    private boolean anonymized;

//    @CsvBindByName(column = "isModified")
    private boolean modified;

//    @CsvBindByName(column = "CssVersion")
    private String cssVersion;

//    @CsvBindByName(column = "AolVersion")
    private String aolVersion;

//    @CsvBindByName(column = "Device_Name")
    private String deviceName;

//    @CsvBindByName(column = "Device_Maker")
    private String deviceMaker;

//    @CsvBindByName(column = "Device_Type")
    private String deviceType;

//    @CsvBindByName(column = "Device_Pointing_Method")
    private String devicePointingMethod;

//    @CsvBindByName(column = "Device_Code_Name")
    private String deviceCodeName;

//    @CsvBindByName(column = "Device_Brand_Name")
    private String deviceBrandName;

//    @CsvBindByName(column = "RenderingEngine_Name")
    private String renderingEngineName;

//    @CsvBindByName(column = "RenderingEngine_Version")
    private String renderingEngineVersion;

//    @CsvBindByName(column = "RenderingEngine_Description")
    private String renderingEngineDescription;

//    @CsvBindByName(column = "RenderingEngine_Maker")
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

    public void setMasterParent(boolean masterParent) {
        this.masterParent = masterParent;
    }

    public void setLiteMode(boolean liteMode) {
        this.liteMode = liteMode;
    }

    public void setBrowserBits(int browserBits) {
        this.browserBits = browserBits;
    }

    public void setPlatformBits(double platformBits) {
        this.platformBits = platformBits;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

    public void setBeta(boolean beta) {
        this.beta = beta;
    }

    public void setWin16(boolean win16) {
        this.win16 = win16;
    }

    public void setWin32(boolean win32) {
        this.win32 = win32;
    }

    public void setWin64(boolean win64) {
        this.win64 = win64;
    }

    public void setFrames(boolean frames) {
        this.frames = frames;
    }

    public void setiFrames(boolean iFrames) {
        this.iFrames = iFrames;
    }

    public void setTables(boolean tables) {
        this.tables = tables;
    }

    public void setCookies(boolean cookies) {
        this.cookies = cookies;
    }

    public void setBackgroundSounds(boolean backgroundSounds) {
        this.backgroundSounds = backgroundSounds;
    }

    public void setJavaScript(boolean javaScript) {
        this.javaScript = javaScript;
    }

    public void setvBScript(boolean vBScript) {
        this.vBScript = vBScript;
    }

    public void setJavaApplets(boolean javaApplets) {
        this.javaApplets = javaApplets;
    }

    public void setActiveXControls(boolean activeXControls) {
        this.activeXControls = activeXControls;
    }

    public void setCrawler(boolean crawler) {
        this.crawler = crawler;
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
