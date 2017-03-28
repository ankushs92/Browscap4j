package in.ankushs.browscap4j.service.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

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
        try (CSVReader csvReader = new CSVReader(new FileReader(file), CSVParser.DEFAULT_SEPARATOR,
                CSVParser.DEFAULT_QUOTE_CHARACTER, 2);) {
            HeaderColumnNameMappingStrategy<CsvBrowserCapabilities> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(CsvBrowserCapabilities.class);

            CsvToBean<CsvBrowserCapabilities> csvToBean = new CsvToBean<>();
            return csvToBean.parse(strategy, csvReader);
        } catch (FileNotFoundException e) {
            LOGGER.error("Couldn't find file {}", file.getAbsoluteFile());
        } catch (IOException e) {
            LOGGER.error("Couldn't read file {}", file.getAbsoluteFile());
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
        try (CSVReader reader = new CSVReader(new FileReader(file), CSVParser.DEFAULT_SEPARATOR,
                CSVParser.DEFAULT_QUOTE_CHARACTER, 1);) {
            String[] nextLine = reader.readNext();
            return nextLine;
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
        return getRecords().stream().collect(
                Collectors.toMap(browserCapability -> browserCapability.getPropertyName(), browserCapability -> {
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
                }));
    }

}
