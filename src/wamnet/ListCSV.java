package wamnet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class ListCSV {
	
	private Iterator<String[]> it;

	public ListCSV(File csv_file) throws IOException {
    	String[] line;
    	List<String[]> list = new ArrayList<String[]>();

		CSVReader reader = new CSVReader(new FileReader(csv_file));
    	reader.readNext();
    	
    	while ((line = reader.readNext()) != null) {
    		list.add(line);
    	}
    	
    	it = list.iterator();
    	reader.close();
	}
	
	public Iterator<String[]> getAll() {
		return it;
	}
	
	public String[] next() {
		if (it.hasNext() == true) {
    		return it.next();
		} else {
			return null;
	}
	}
	
}
