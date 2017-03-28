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

    private File file;

    private JsonParsingService(File file) {
        this.file = file;
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
        try {
            // read json file data to String
            byte[] jsonData = Files.readAllBytes(file.toPath());

            // create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // read JSON like DOM Parser
            return objectMapper.readTree(jsonData);
        } catch (IOException ioe) {
            LOGGER.error("Couldn't parse Json file {}", file.getAbsolutePath());
        }
        return null;
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
        ObjectMapper mapper = new ObjectMapper();
        return StreamSupport.stream(iterable.spliterator(), false).filter(node -> {
            return !EXCLUDED_PATH.contains(node.getKey());
        }).collect(Collectors.toMap(entry -> entry.getKey(), entry -> {
            JsonBrowserCapabilities browserCapability = null;
            try {
                browserCapability = mapper.readValue(entry.getValue().asText(), JsonBrowserCapabilities.class);
                return new BrowserCapabilities.Builder().browser(browserCapability.getBrowser())
                        .browserType(browserCapability.getBrowserType())
                        .deviceCodeName(browserCapability.getDeviceCodeName())
                        .deviceName(browserCapability.getDeviceName())
                        .deviceBrandName(browserCapability.getDeviceBrandName())
                        .deviceType(browserCapability.getDeviceType()).platform(browserCapability.getPlatform())
                        .platformMaker(browserCapability.getPlatformMaker())
                        .platformVersion(browserCapability.getPlatformVersion()).isTablet(browserCapability.isTablet())
                        .isMobile(browserCapability.isMobileDevice()).build();
            } catch (IOException e) {
                LOGGER.error("Couldn't parse {}", entry.getValue().asText());
            }
            return null;
        }));
    }

}
