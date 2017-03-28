package in.ankushs.browscap4j.service;

import java.io.File;

import in.ankushs.browscap4j.service.csv.CsvParsingService;
import in.ankushs.browscap4j.service.json.JsonParsingService;
import in.ankushs.browscap4j.service.xml.XmlParsingService;

public class ParsingServiceFactory {

    public static ParsingService getParsingService(File file) {
        if (file.getAbsolutePath().endsWith(".xml")) {
            return XmlParsingService.getInstance(file);
        } else if (file.getAbsolutePath().endsWith(".json")) {
            return JsonParsingService.getInstance(file);
        } else {
            return CsvParsingService.getInstance(file);
        }
    }

}
