package in.ankushs.browscap4j.service.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.service.ParsingService;

public class XmlParsingService implements ParsingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParsingService.class);

    private static XmlParsingService service;

    private Browsercaps browsercaps;

    private XmlParsingService(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Browsercaps.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            browsercaps = (Browsercaps) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            LOGGER.error("Couldn't parse xml file {}", file.getAbsolutePath());
        }
    }

    /**
     * Creates a Singelton object.
     */
    public static XmlParsingService getInstance(File file) {
        if (service == null) {
            return new XmlParsingService(file);
        }
        return service;
    }

    @Override
    public Long getVersion() {
        return getBrowsercaps().getVersion();
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return getBrowsercaps().getReleaseDate();
    }

    @Override
    public List<String> getNamePatterns() {
        return getBrowsercaps().getBrowscapitems();
    }

    private Browsercaps getBrowsercaps() {
        return browsercaps;
    }

    @Override
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return getBrowsercaps().getBrowscapitemsMap().entrySet().stream().collect(Collectors.toConcurrentMap(entry -> {
            return entry.getKey();
        }, entry -> {
            Browscapitem browscapitem = entry.getValue();
            final String browser = (getString(browscapitem, "Browser")).intern();
            final String browserType = (getString(browscapitem, "Browser_Type")).intern();
            final String deviceName = (getString(browscapitem, "Device_Name")).intern();
            final String deviceType = (getString(browscapitem, "Device_Type")).intern();
            final String deviceCodeName = (getString(browscapitem, "Device_Code_Name")).intern();
            final String deviceBrandName = (getString(browscapitem, "Device_Brand_Name")).intern();
            final String platform = (getString(browscapitem, "Platform")).intern();
            final String platformMaker = (getString(browscapitem, "Platform_Maker")).intern();
            final String platformVersion = getString(browscapitem, "Platform_Version");
            final boolean isMobile = getBoolean(browscapitem, "isMobileDevice");
            final boolean isTablet = getBoolean(browscapitem, "isTablet");
            return new BrowserCapabilities.Builder().browser(browser).browserType(browserType)
                    .deviceCodeName(deviceCodeName).deviceName(deviceName).deviceBrandName(deviceBrandName)
                    .deviceType(deviceType).platform(platform).platformMaker(platformMaker)
                    .platformVersion(platformVersion).isTablet(isTablet).isMobile(isMobile).build();
        }, (v1, v2) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", v1));
        }, ConcurrentHashMap::new));
    }

    private String getString(Browscapitem browscapitem, String search) {
        return getString(browscapitem, search, "Unknown");
    }

    private String getString(Browscapitem browscapitem, String search, String defaultValue) {
        String property = browscapitem.getString(search);
        if (property != null) {
            return property;
        }
        String parent = browscapitem.getString("Parent");
        if (parent != null) {
            return getString(getBrowsercaps().getBrowscapitemsMap().get(parent), search);
        }
        return defaultValue;
    }

    private boolean getBoolean(Browscapitem browscapitem, String search) {
        return getBoolean(browscapitem, search, false);
    }

    private boolean getBoolean(Browscapitem browscapitem, String search, boolean defaultValue) {
        Boolean property = browscapitem.getBoolean(search);
        if (property != null) {
            return property.booleanValue();
        }
        String parent = browscapitem.getString("Parent");
        if (parent != null) {
            return getBoolean(getBrowsercaps().getBrowscapitemsMap().get(parent), search);
        }
        return defaultValue;

    }

}
