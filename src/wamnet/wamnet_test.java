package wamnet;



import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;

 public class wamnet_test {
	
	 private static String read_url = "http://www.kaigokensaku.jp/12/index.php?action_kouhyou_pref_search_list_list=true&PrefCd=12";
	 private static String csv_file_name = "wamnet_output.csv";
	 private static int turn_for = 3000;
	 
	static CSVWriter writer;
 
    public static void main(String[] args) throws IOException, InterruptedException {
        RemoteWebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        writer  = new CSVWriter(new FileWriter(csv_file_name));
        writer.flush();
        
        driver.get(read_url);
        //driver.executeScript("pagerForm(5000,5,null,null);");
        driver.switchTo().frame(driver.findElement(By.id("resultListFrame")));
        for (int i=1;i<=turn_for;i++) {
            searchResultTable(driver);
            if(i%200 == 5) {
              reloadTable(driver,i);
            }
        }
        driver.quit();
      	writer.close();
        
    }
    
    private static void searchResultTable(WebDriver driver) throws IOException, InterruptedException {        
        String box_xpath = "//*[@id=\"searchResult\"]//li[@class=\"listLi\"]";
        List<WebElement> list_box = driver.findElements(By.xpath(box_xpath));
        ResultLine line;
        
        for(WebElement box: list_box) {
        	
            line = new ResultLine(box, driver);
            line.getResult();
        	line.exportCSV(writer);
        }
        
        int i = 0;
        while (!clickNextLink(driver)) {
         Thread.sleep(1000);
         i++;
         if (i > 10) throw new UnreachableBrowserException("�K��񐔂��I�[�o�[���܂����B ClickNextLink");
        }
    }
    
    private static boolean clickNextLink(WebDriver driver) {
        try {
            driver.findElement(By.partialLinkText("����")).click(); 
            return true;            
        } catch(UnreachableBrowserException e) {
        	return false;
        }
    }
    
    private static void reloadTable(RemoteWebDriver driver, int page) {
    	System.out.println("reload Table");
    	WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(read_url);
        driver.switchTo().frame(driver.findElement(By.id("resultListFrame")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("����")));
        driver.executeScript("pagerForm(" + ((page-1) * 5) + ",5,null,null);");
    }
    
}