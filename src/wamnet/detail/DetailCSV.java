package wamnet.detail;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.opencsv.CSVWriter;

public class DetailCSV  {

	private String output_csv = "csv/wamnet_detail";
	private CSVWriter writer;

	
	public DetailCSV() throws IOException {
	    SimpleDateFormat now = new SimpleDateFormat("yyyyMMddHHmmss");
		String file_name = output_csv + now.format(Calendar.getInstance().getTime()) + ".csv";
		writer = new CSVWriter(new FileWriter(file_name, true));
	}
	
	public void writeHeader(HashMap<String,String> map) throws IOException {
		String[] str = map.keySet().toArray(new String[0]);
    	writer.writeNext(str);
    	writer.flush();
		
	}


	public void writeLine(HashMap<String,String> map) throws IOException {
		String[] str = map.values().toArray(new String[0]);
    	writer.writeNext(str);
    	writer.flush();
	}
	
}
