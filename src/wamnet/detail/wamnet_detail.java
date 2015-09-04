package wamnet.detail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import wamnet.ListCSV;


public class wamnet_detail {

	private static String csv_file_name = "csv/wamnet_input.csv";


	public static void main(String[] args) throws IOException,
			InterruptedException {

		RemoteWebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		ListCSV list_csv = new ListCSV(new File(csv_file_name));
		DetailCSV detail_csv = new DetailCSV();
		String[] line;
		
		
     	while ((line = list_csv.next()) != null) {
				DetailPage page = new DetailPage(driver);
				HashMap<String, String> map = page.accessURL(line[6].replace("kani", "kihon")).getMap();
				detail_csv.writeLine(map,line);
		}

	}

}
