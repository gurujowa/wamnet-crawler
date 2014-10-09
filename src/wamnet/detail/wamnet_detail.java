package wamnet.detail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import wamnet.ListCSV;
import wamnet.ListCSVLine;


public class wamnet_detail {

	private static String csv_file_name = "csv/wamnet_input.csv";

	private static WebDriver driver;

	public static void main(String[] args) throws IOException,
			InterruptedException {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		ListCSV list_csv = new ListCSV(new File(csv_file_name));
		DetailCSV detail_csv = new DetailCSV();
		DetailPage page;
		ListCSVLine line;
		
		
			while ((line = list_csv.next()) != null) {
				page = new DetailPage(driver);
				page.accessURL(line.getUrl().replace("kani", "kihon"));
				HashMap<String, String> map = page.getMap();
				detail_csv.writeLine(map);
			}

	}

}
