package in.ankushs.browscap4j.service;

import java.io.File;

public class ParsingServiceFactory {

    public static ParsingService getParsingService(File file) {
        if (file.getAbsolutePath().endsWith(".xml")) {
            return XMLParsingService.getInstance(file);
        } else {
            return CSVParsingService.getInstance(file);
        }
    }

}
