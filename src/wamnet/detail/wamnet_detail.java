package wamnet.detail;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.opencsv.CSVReader;


public class wamnet_detail {

	private static String csv_file_name = "csv/wamnet_input.csv";


	public static void main(String[] args) throws IOException,
			InterruptedException {

		RemoteWebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		DetailCSV detail_csv = new DetailCSV();
		String[] line;
		
		CSVReader reader = new CSVReader(new FileReader(csv_file_name));
		
		int iteration = 0;
     	while ((line = reader.readNext()) != null) {
				DetailPage page = new DetailPage(driver);
				HashMap<String, String> map = page.accessURL(line[6].replace("kani", "kihon")).getMap();
			    if(iteration == 0) {
					detail_csv.writeHeader(map);
			        iteration++;  
			    } 
				detail_csv.writeLine(map,line);
		}
     	
     	reader.close();

	}

}
