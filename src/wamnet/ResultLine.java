package wamnet;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;

import au.com.bytecode.opencsv.CSVWriter;

public class ResultLine {
	
	private WebDriver driver;
	private String[] result;
	private String tr_xpath;
	
	public ResultLine(String tr_xpath, WebDriver dr) {
		this.driver = dr;
		result = new String[7];
		this.tr_xpath = tr_xpath;
	}
	
	public void getResult () throws MalformedURLException, InterruptedException {
		readXPath();		
	}

	private String getResultURL(String tr_path) throws MalformedURLException {
		String td_xpath = tr_xpath + "/td[5]/div[1]/input";
		By xpath = By.xpath(td_xpath);
    	WebElement el =  this.driver.findElement(xpath);    	
    	String onclick = el.getAttribute("onclick").replace("parent.location.href='index.php", "").replace("';", "");
    	URL current_url = new URL(driver.getCurrentUrl());
    	return "http://" + current_url.getHost() + current_url.getPath() + onclick;
	}
	
	private boolean readXPath() throws MalformedURLException {
		int error = 0;
		do {
			if (setByXpath()) {
				return true;
			}
			sleep(5000);
			error++;
		} while (error <= 5);
		
		
		throw new NoSuchElementException("‹K’è‚Ì“Ç‚Ýž‚ÝŽ¸”s‰ñ”‚ð’´‚¦‚Ü‚µ‚½B" + tr_xpath);
	}
	
    private static void sleep(int microtime) {
        try {
            Thread.sleep(microtime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	private boolean setByXpath() throws MalformedURLException {
		try {
			result[0] = getName(tr_xpath);
			result[1] = getZipcode(tr_xpath);
			result[2] = getAddress(tr_xpath);
			result[3] = getTel(tr_xpath);
			result[4] = getFax(tr_xpath);
			result[5] = getCategory(tr_xpath);		
			result[6] = getResultURL(this.tr_xpath);
			
			return true;
		} catch(NoSuchElementException e) {
			return false;
		} catch(UnreachableBrowserException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
			sleep(30000);
			return false;
		}
	}

	
	private String getName(String tr_xpath) {
		String td_xpath = tr_xpath + "/td[3]/div[1]/a";
		By xpath = By.xpath(td_xpath);
    	WebElement el =  this.driver.findElement(xpath);    	
    	return el.getText();
	}

	private String getZipcode(String tr_xpath) {
		WebElement el =  this.driver.findElement(By.xpath(tr_xpath + "/td[3]/div[2]"));    	
    	return el.getText();
	}

	private String getAddress(String tr_xpath) {
		WebElement el =  this.driver.findElement(By.xpath(tr_xpath + "/td[3]/div[3]"));    	
    	return el.getText();
	}

	private String getTel(String tr_xpath) {
		WebElement el =  this.driver.findElement(By.xpath(tr_xpath + "/td[4]/div[1]"));    	
    	String text =  el.getText();
    	text = text.replace("]", "-");
    	text = text.replace("[", "-");
    	return zenHan(text);
	}	

	private String getFax(String tr_xpath) {
		WebElement el =  this.driver.findElement(By.xpath(tr_xpath + "/td[4]/div[2]"));    	
    	String text = el.getText();
    	text = text.replace("]", "-");
    	text = text.replace("[", "-");
    	return zenHan(text);
	}	
	
	private String getCategory(String tr_xpath) {
		WebElement el =  this.driver.findElement(By.xpath(tr_xpath + "/td[6]/div[1]"));    	
    	return el.getText();
	}
	
	public void exportCSV(CSVWriter writer) {
    	writer.writeNext(result);		
	}

	 /**
	   * ‘SŠp”Žš‚ð”¼Šp‚É•ÏŠ·‚µ‚Ü‚·B
	   * @param s •ÏŠ·Œ³•¶Žš—ñ
	   * @return •ÏŠ·Œã•¶Žš—ñ
	   */
	private static String zenHan(String s) {
	    StringBuffer sb = new StringBuffer(s);
	    for (int i = 0; i < sb.length(); i++) {
	      char c = sb.charAt(i);
	      if (c >= '‚O' && c <= '‚X') {
	        sb.setCharAt(i, (char)(c - '‚O' + '0'));
	      }
	    }
	    return sb.toString();
	  }
}
