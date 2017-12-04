package in.ankushs.browscap4j.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by ankushsharma on 04/12/17.
 */
@Builder
@Getter
@ToString
public class BrowserCapabilities {

    private final String browser;
    private final String browserVersion;
    private final BrowserType browserType;
    private final Bits browserBits;
    private final String browserMaker;
    private final String platform;
    private final String platformVersion;
    private final Bits platformBits;
    private final String platformMaker;
    private final String deviceName;
    private final String deviceMaker;
    private final DeviceType deviceType;
    private final DevicePointingMethod devicePointingMethod;
    private final String deviceBrandName;


}

