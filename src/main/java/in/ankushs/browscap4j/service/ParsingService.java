package in.ankushs.browscap4j.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * 
 * @author Ankush Sharma
 *
 */
public interface ParsingService {
	List<String[]> getRecords(InputStream inputStream) throws IOException;

	List<String[]> getRecords(final File file) throws IOException;
}
