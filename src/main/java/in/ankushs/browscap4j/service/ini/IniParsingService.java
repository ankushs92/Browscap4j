package in.ankushs.browscap4j.service.ini;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.service.ParsingService;

public class IniParsingService implements ParsingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IniParsingService.class);

    private static IniParsingService service;

    private File file;

    private IniParsingService(File file) {
        this.file = file;
    }

    /**
     * Creates a Singelton object.
     */
    public static IniParsingService getInstance(File file) {
        if (service == null) {
            return new IniParsingService(file);
        }
        return service;
    }

    private Ini getIni() {
        try {
            Config config = new Config();
            config.setTree(false);
            Ini ini = new Ini();
            ini.setConfig(config);
            ini.load(new FileReader(file));
            return ini;
        } catch (IOException ioe) {
            LOGGER.error("Couldn't parse ini file", file.getAbsolutePath());
        }
        return null;
    }

    @Override
    public Long getVersion() {
        String version = getIni().get("GJK_Browscap_Version").get("Version");
        return Long.parseLong(version);
    }

    @Override
    public LocalDateTime getReleaseDate() {
        String version = getIni().get("GJK_Browscap_Version").get("Released");
        DateTimeFormatter f = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT, Locale.ENGLISH);
        return LocalDateTime.parse(version, f);
    }

    @Override
    public List<String> getNamePatterns() {
        return getIni().keySet().stream().filter(section -> !section.equals("GJK_Browscap_Version"))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return getIni().entrySet().stream().filter(entry -> !entry.getKey().equals("GJK_Browscap_Version"))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
                    Section browscapitem = entry.getValue();
                    final String browser = (getString(browscapitem, "Browser")).intern();
                    final String browserType = (getString(browscapitem, "Browser_Type")).intern();
                    final String deviceName = (getString(browscapitem, "Device_Name")).intern();
                    final String deviceType = (getString(browscapitem, "Device_Type")).intern();
                    final String deviceCodeName = (getString(browscapitem, "Device_Code_Name")).intern();
                    final String deviceBrandName = (getString(browscapitem, "Device_Brand_Name")).intern();
                    final String platform = (getString(browscapitem, "Platform")).intern();
                    final String platformMaker = (getString(browscapitem, "Platform_Maker")).intern();
                    final String platformVersion = (getString(browscapitem, "Platform_Version")).intern();
                    final boolean isMobile = getBoolean(browscapitem, "isMobileDevice");
                    final boolean isTablet = getBoolean(browscapitem, "isTablet");
                    return new BrowserCapabilities.Builder().browser(browser).browserType(browserType)
                            .deviceCodeName(deviceCodeName).deviceName(deviceName).deviceBrandName(deviceBrandName)
                            .deviceType(deviceType).platform(platform).platformMaker(platformMaker)
                            .platformVersion(platformVersion).isTablet(isTablet).isMobile(isMobile).build();
                }));
    }

    private String getString(Section section, String search) {
        return getString(section, search, "Unknown");
    }

    private String getString(Section section, String search, String defaultValue) {
        if (section.containsKey(search)) {
            return section.get(search).replaceAll("\"", "");
        }
        return defaultValue;
    }

    private boolean getBoolean(Section section, String search) {
        return getBoolean(section, search, false);
    }

    private boolean getBoolean(Section section, String search, boolean defaultValue) {
        if (section.containsKey(search)) {
            return Boolean.parseBoolean(section.get(search).replaceAll("\"", ""));
        }
        return defaultValue;
    }

}
