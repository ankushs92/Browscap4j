package in.ankushs.browscap4j.service;

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
import in.ankushs.browscap4j.xml.Browsercaps;
import in.ankushs.browscap4j.xml.Item;

public class XMLParsingService implements ParsingService {

    private static final Logger logger = LoggerFactory.getLogger(XMLParsingService.class);

    private static XMLParsingService service;

    private File file;

    private XMLParsingService(File file) {
        this.file = file;
    }

    /**
     * Creates a Singelton object.
     */
    public static XMLParsingService getInstance(File file) {
        if (service == null) {
            return new XMLParsingService(file);
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
            logger.error("Couldn't ");
        }
        return null;
    }

    @Override
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return readXML().getBrowscapitems().stream().collect(Collectors.toMap(browscapitem -> {
            return browscapitem.getName();
        }, browscapitem -> {

            final String browser = (browscapitem.getItems().containsKey("Browser")
                    ? browscapitem.getItems().get("Browser").getValue() : UNKNOWN).intern();
            final String browserType = (browscapitem.getItems().containsKey("Browser_Type")
                    ? browscapitem.getItems().get("Browser_Type").getValue() : UNKNOWN).intern();
            final String deviceName = (browscapitem.getItems().containsKey("Device_Name")
                    ? browscapitem.getItems().get("Device_Name").getValue() : UNKNOWN).intern();
            final String deviceType = (browscapitem.getItems().containsKey("Device_Type")
                    ? browscapitem.getItems().get("Device_Type").getValue() : UNKNOWN).intern();
            final String deviceCodeName = (browscapitem.getItems().containsKey("Device_Code_Name")
                    ? browscapitem.getItems().get("Device_Code_Name").getValue() : UNKNOWN).intern();
            final String deviceBrandName = (browscapitem.getItems().containsKey("Device_Brand_Name")
                    ? browscapitem.getItems().get("Device_Brand_Name").getValue() : UNKNOWN).intern();
            final String platform = (browscapitem.getItems().containsKey("Platform")
                    ? browscapitem.getItems().get("Platform").getValue() : UNKNOWN).intern();
            final String platformMaker = (browscapitem.getItems().containsKey("Platform_Maker")
                    ? browscapitem.getItems().get("Platform_Maker").getValue() : UNKNOWN).intern();
            final String platformVersion = (browscapitem.getItems().containsKey("Platform_Version")
                    ? browscapitem.getItems().get("Platform_Version").getValue() : UNKNOWN).intern();
            final boolean isMobile = Boolean.valueOf(browscapitem.getItems().get("isMobileDevice").getValue());
            final boolean isTablet = Boolean.valueOf(browscapitem.getItems().get("isTablet").getValue());

            return new BrowserCapabilities.Builder().browser(browser).browserType(browserType)
                    .deviceCodeName(deviceCodeName).deviceName(deviceName).deviceBrandName(deviceBrandName)
                    .deviceType(deviceType).platform(platform).platformMaker(platformMaker)
                    .platformVersion(platformVersion).isTablet(isTablet).isMobile(isMobile).build();
        }));
    }

}
