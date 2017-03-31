package in.ankushs.browscap4j.service.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.service.ParsingService;

/**
 * Singleton class for parsing csv files.
 * 
 * @author Ankush Sharma
 */
public final class CsvParsingService implements ParsingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParsingService.class);

    private static CsvParsingService service;

    private File file;

    private CsvParsingService(File file) {
        this.file = file;
    }

    /**
     * Creates a Singelton object.
     */
    public static CsvParsingService getInstance(File file) {
        if (service == null) {
            return new CsvParsingService(file);
        }
        return service;
    }

    private List<CsvBrowserCapabilities> getRecords() {
        try {
            CsvMapper mapper = new CsvMapper();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i < 2; i++) {
                reader.readLine();
            }
            MappingIterator<CsvBrowserCapabilities> it = mapper.readerFor(CsvBrowserCapabilities.class)
                    .with(CsvSchema.emptySchema().withHeader()).readValues(reader);
            return it.readAll();
        } catch (IOException e) {
            LOGGER.error("Failed to Parse csv file {}", file);
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getNamePatterns() {
        return getRecords().stream().map(browserCapability -> {
            return browserCapability.getPropertyName();
        }).sorted((record1, record2) -> {
            return -Integer.compare(record1.length(), record2.length());
        }).collect(Collectors.toList());
    }

    @Override
    public Long getVersion() {
        String[] line = getSecondLine();
        return Long.valueOf(line[0]);
    }

    private String[] getSecondLine() {
        try {
            CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            MappingIterator<String[]> it = mapper.readerFor(String[].class).readValues(reader);
            it.next();
            return it.next();
        } catch (IOException e) {
            LOGGER.error("Couldn't parse CSV file {}", file.getAbsoluteFile());
        }
        return new String[2];
    }

    @Override
    public LocalDateTime getReleaseDate() {
        String[] line = getSecondLine();
        DateTimeFormatter f = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT, Locale.ENGLISH);
        return LocalDateTime.parse(line[1], f);
    }

    /**
     * Creates a <a href=
     * "https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html">HashMap</a>
     * with name pattern as key and browser capabilities as a
     * BrowserCapabilities object as value.
     * 
     * @return a HashMap with name pattern as key and BrowserCapabilities as
     * value
     */
    @Override
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return getRecords().stream().collect(Collectors
                .toConcurrentMap(browserCapability -> browserCapability.getPropertyName(), browserCapability -> {
                    return new BrowserCapabilities.Builder().browser(browserCapability.getBrowser())
                            .browserType(browserCapability.getBrowserType())
                            .deviceCodeName(browserCapability.getDeviceCodeName())
                            .deviceName(browserCapability.getDeviceName())
                            .deviceBrandName(browserCapability.getDeviceBrandName())
                            .deviceType(browserCapability.getDeviceType()).platform(browserCapability.getPlatform())
                            .platformMaker(browserCapability.getPlatformMaker())
                            .platformVersion(browserCapability.getPlatformVersion())
                            .isTablet(browserCapability.isTablet()).isMobile(browserCapability.isMobileDevice())
                            .build();
                }, (v1, v2) -> {
                    throw new IllegalStateException("Duplicated key");
                }, ConcurrentHashMap::new));
    }

}
