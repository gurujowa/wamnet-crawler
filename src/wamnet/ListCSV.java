package wamnet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class ListCSV {
	
	private Iterator<ListCSVLine> it;

	public ListCSV(File csv_file) throws IOException {
    	String [] line;
    	ListCSVLine csv;
    	List<ListCSVLine> list = new ArrayList<ListCSVLine>();

		CSVReader reader = new CSVReader(new FileReader(csv_file));
    	reader.readNext();
    	
    	while ((line = reader.readNext()) != null) {
    		csv = new ListCSVLine(line);
    		list.add(csv);
    	}
    	
    	it = list.iterator();
    	reader.close();
	}
	
	public Iterator<ListCSVLine> getAll() {
		return it;
	}
	
	public ListCSVLine next() {
		if (it.hasNext() == true) {
    		return it.next();
		} else {
			return null;
	}
	}
	
}
