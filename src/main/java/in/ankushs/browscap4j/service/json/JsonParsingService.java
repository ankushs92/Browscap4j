package in.ankushs.browscap4j.service.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.service.ParsingService;
import in.ankushs.browscap4j.service.csv.CsvParsingService;

public class JsonParsingService implements ParsingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParsingService.class);

    private static final List<String> EXCLUDED_PATH = Arrays.asList("comments", "GJK_Browscap_Version");

    private static JsonParsingService service;

    private JsonNode root;

    private ObjectMapper mapper = new ObjectMapper();

    private JsonParsingService(File file) {
        try {
            byte[] jsonData = Files.readAllBytes(file.toPath());
            ObjectMapper objectMapper = new ObjectMapper();
            root = objectMapper.readTree(jsonData);
        } catch (IOException ioe) {
            LOGGER.error("Couldn't parse Json file {}", file.getAbsolutePath());
        }
    }

    /**
     * Creates a Singelton object.
     */
    public static JsonParsingService getInstance(File file) {
        if (service == null) {
            return new JsonParsingService(file);
        }
        return service;
    }

    public JsonNode getJsonRoot() {
        return root;
    }

    @Override
    public Long getVersion() {
        JsonNode version = getJsonRoot().get("GJK_Browscap_Version").get("Version");
        return version.asLong();
    }

    @Override
    public LocalDateTime getReleaseDate() {
        JsonNode version = getJsonRoot().get("GJK_Browscap_Version").get("Released");
        DateTimeFormatter f = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT, Locale.ENGLISH);
        return LocalDateTime.parse(version.asText(), f);
    }

    @Override
    public List<String> getNamePatterns() {
        Iterable<String> iterable = () -> getJsonRoot().fieldNames();
        return StreamSupport.stream(iterable.spliterator(), false).filter(node -> {
            return !EXCLUDED_PATH.contains(node);
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        Iterable<Map.Entry<String, JsonNode>> iterable = () -> getJsonRoot().fields();
        return StreamSupport.stream(iterable.spliterator(), false).filter(node -> {
            return !EXCLUDED_PATH.contains(node.getKey());
        }).collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
            JsonNode browscapitem = null;
            try {
                browscapitem = mapper.readTree(entry.getValue().asText());
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
            } catch (IOException e) {
                LOGGER.error("Couldn't parse {}", entry.getValue().asText());
            }
            return null;
        }, (v1, v2) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", v1));
        }, ConcurrentHashMap::new));
    }

    private String getString(JsonNode browscapitem, String search) {
        return getString(browscapitem, search, "Unknown");
    }

    private String getString(JsonNode browscapitem, String search, String defaultValue) {
        JsonNode property = browscapitem.get(search);
        if (property != null) {
            return property.asText();
        }
        String parent = browscapitem.get("Parent").asText();
        if (parent != null) {
            try {
                return getString(mapper.readTree(getJsonRoot().get(parent).asText()), search);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    private boolean getBoolean(JsonNode browscapitem, String search) {
        return getBoolean(browscapitem, search, false);
    }

    private boolean getBoolean(JsonNode browscapitem, String search, boolean defaultValue) {
        JsonNode property = browscapitem.get(search);
        if (property != null) {
            return property.asBoolean();
        }
        String parent = browscapitem.get("Parent").asText();
        if (parent != null) {
            try {
                return getBoolean(mapper.readTree(getJsonRoot().get(parent).asText()), search);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return defaultValue;

    }
}
