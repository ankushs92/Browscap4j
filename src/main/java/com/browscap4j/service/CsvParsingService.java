package com.browscap4j.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

public final class CsvParsingService implements ParsingService {
	
	private static CsvParsingService csvParsingService;
	
	public static CsvParsingService getInstance(){
		if(csvParsingService==null){
			return new CsvParsingService();
		}
		return csvParsingService;
	}

	@Override
	public List<String[]> getRecords(final File file) throws IOException {
		final CSVReader csvReader = new CSVReader(new FileReader(file));
		final List<String[]> records = csvReader.readAll();
		//Different versions of the Csv file have different headers.
		//We consider that each record is a String array ,where each array contains atleast 43 records.
		return records.stream()
				  .filter(record -> record.length > 43 )
				  .collect(Collectors.toList());
	}
	
}
