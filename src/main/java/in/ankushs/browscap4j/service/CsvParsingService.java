package in.ankushs.browscap4j.service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
/**
 * Singleton class for parsing csv files.
 * @author Ankush Sharma
 */
public final class CsvParsingService implements ParsingService {
		
	private static CsvParsingService csvParsingService;
	/**
	 * Creates a Singelton object.
	 */
	public static CsvParsingService getInstance(){
		if(csvParsingService==null){
			return new CsvParsingService();
		}
		return csvParsingService;
	}
	/**
	 * Parses a csv file. 
	 * @param inputStream The browscap.csv file as a InputStream object.
	 * @return a List of String arrays,where a String array represents one csv record.
	 * @throws IOException
	 */
	@Override
	public List<String[]> getRecords(final InputStream inputStream) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		return getRecords(inputStreamReader);
	}
	/**
	 * Parses a csv file.
	 * @param file The browscap.csv file as a File object.
	 * @return a List of String arrays,where a String array represents one csv record.
	 * @throws IOException
	 */
	@Override
	public List<String[]> getRecords(final File file) throws IOException {
		InputStreamReader inputStreamReader = new FileReader(file);
		return getRecords(inputStreamReader);
	}
	/**
	 * Parses a csv file.
	 * @param inputStreamReader The browscap.csv file as a InputStreamReader object.
	 * @return a List of String arrays,where a String array represents one csv record.
	 * @throws IOException
	 */
	private List<String[]> getRecords(final InputStreamReader inputStreamReader) throws IOException {
		final CSVReader csvReader = new CSVReader(inputStreamReader);
		final List<String[]> records = csvReader.readAll();
		csvReader.close();
		//Different versions of the Csv file have different headers.
		//We consider that each record is a String array ,where each array contains atleast 43 records.
		return records.stream()
				.filter( record -> record.length > 43 )
				.collect(Collectors.toList());
	}
	
}
