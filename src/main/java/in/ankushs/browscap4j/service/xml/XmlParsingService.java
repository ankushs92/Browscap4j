package in.ankushs.browscap4j.service.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    private File file;

    private XmlParsingService(File file) {
        this.file = file;
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
        Item versionItem = readXML().getBrowscapVersion().get("Version");
        return Long.valueOf(versionItem.getValue());
    }

    @Override
    public LocalDateTime getReleaseDate() {
        Item releaseItem = readXML().getBrowscapVersion().get("Released");
        DateTimeFormatter f = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT, Locale.ENGLISH);
        return LocalDateTime.parse(releaseItem.getValue(), f);
    }

    @Override
    public List<String> getNamePatterns() {
        return readXML().getBrowscapitems().stream().map(browscapitem -> {
            return browscapitem.getName();
        }).collect(Collectors.toList());
    }

    private Browsercaps readXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Browsercaps.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Browsercaps) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            LOGGER.error("Couldn't parse xml file {}", file.getAbsolutePath());
        }
        return null;
    }

    @Override
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return readXML().getBrowscapitems().stream().collect(Collectors.toMap(browscapitem -> {
            return browscapitem.getName();
        }, browscapitem -> {
            final String browser = (browscapitem.get("Browser")).intern();
            final String browserType = (browscapitem.get("Browser_Type")).intern();
            final String deviceName = (browscapitem.get("Device_Name")).intern();
            final String deviceType = (browscapitem.get("Device_Type")).intern();
            final String deviceCodeName = (browscapitem.get("Device_Code_Name")).intern();
            final String deviceBrandName = (browscapitem.get("Device_Brand_Name")).intern();
            final String platform = (browscapitem.get("Platform")).intern();
            final String platformMaker = (browscapitem.get("Platform_Maker")).intern();
            final String platformVersion = (browscapitem.get("Platform_Version")).intern();
            final boolean isMobile = Boolean.valueOf(browscapitem.get("isMobileDevice"));
            final boolean isTablet = Boolean.valueOf(browscapitem.get("isTablet"));
            return new BrowserCapabilities.Builder().browser(browser).browserType(browserType)
                    .deviceCodeName(deviceCodeName).deviceName(deviceName).deviceBrandName(deviceBrandName)
                    .deviceType(deviceType).platform(platform).platformMaker(platformMaker)
                    .platformVersion(platformVersion).isTablet(isTablet).isMobile(isMobile).build();
        }));
    }

}
