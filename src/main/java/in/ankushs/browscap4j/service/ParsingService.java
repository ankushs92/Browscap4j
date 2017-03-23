package in.ankushs.browscap4j.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author Ankush Sharma
 *
 */
public interface ParsingService {
    List<String[]> getRecords(final File file) throws IOException;

    Long getVersion(File file) throws IOException;

    LocalDateTime getReleaseDate(File file) throws IOException;
}
