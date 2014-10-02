package wamnet;



import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import au.com.bytecode.opencsv.CSVWriter;

 public class wamnet_test {
	
	 private static String read_url = "http://www.kaigokensaku.jp/10/index.php?action_kouhyou_pref_search_list_list=true&PrefCd=10";
	 private static String csv_file_name = "wamnet_output.csv";
	 private static int turn_for = 3000;
	 
	static CSVWriter writer;
 
    public static void main(String[] args) throws IOException, InterruptedException {
        RemoteWebDriver driver = new FirefoxDriver();
        writer  = new CSVWriter(new FileWriter(csv_file_name));
        writer.flush();
        
        driver.get(read_url);
 //       driver.executeScript("pagerForm(1750,5,null,null);");
        driver.switchTo().frame(driver.findElement(By.id("resultListFrame")));
        for (int i=1;i<=turn_for;i++) {
            searchResultTable(driver);
        }
        driver.quit();
      	writer.close();
        
    }
    
    private static void searchResultTable(WebDriver driver) throws IOException, InterruptedException {        
        String table_path = "//*[@id='searchResult']/tbody/";
        String tr_xpath;
        ResultLine line;
        
        for(int i=1;i<=5;i++) {
          	tr_xpath = table_path + "tr[" + i + "]";
            line = new ResultLine(tr_xpath, driver);
            line.getResult();
        	line.exportCSV(writer);
        }
        
        int i = 0;
        while (!clickNextLink(driver)) {
         Thread.sleep(1000);
         i++;
         if (i > 10) throw new UnreachableBrowserException("規定回数をオーバーしました。 ClickNextLink");
        }
    }
    
    private static boolean clickNextLink(WebDriver driver) {
        try {
            driver.findElement(By.partialLinkText("次へ")).click(); 
            return true;            
        } catch(UnreachableBrowserException e) {
        	return false;
        }
    }
    
}