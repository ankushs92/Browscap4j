package in.ankushs.browscap4j.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
/**
 * 
 * @author Ankush Sharma
 *
 */
public interface ParsingService {
	List<String[]> getRecords(final File file) throws IOException;
}
