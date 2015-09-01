package wamnet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.opencsv.CSVWriter;

public class ResultLine {

	private WebDriver driver;
	private String[] result;
	private WebElement box;

	public ResultLine(WebElement box, WebDriver dr) {
		this.driver = dr;
		result = new String[7];
		this.box = box;
	}

	public boolean getResult() throws MalformedURLException, InterruptedException {
		try {
			result[0] = getLine("./table/tbody/tr[2]/td/div/a");
			result[1] = getLine("//td[@class=\"listAddress\"]/span[@class=\"postalCode\"]");
			result[2] = getLine("//td[@class=\"listAddress\"]");
			result[3] = getLine("//td[@class=\"tel \"]");
			result[4] = getLine("//td[@class=\"tel \"]");
			result[5] = getLine("//span[@class=\"listService yellow\"]");
			result[6] = getResultURL();
			return true;
		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
			return false;
		} catch (UnreachableBrowserException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
			return false;
		}
	}
	

	private String getLine(String xpath) {
		WebElement el = box.findElement(By.xpath(xpath));
		return el.getText();
	}


	private String getResultURL() throws MalformedURLException {
		WebElement el = box.findElement(By.xpath("//div[@class=\"listFooter\"]//div[@class=\"tableHalfRight\"]//a"));
		String onclick = el.getAttribute("onclick").replace("';", "");
		URL current_url = new URL(driver.getCurrentUrl());
		return "http://" + current_url.getHost() + current_url.getPath() + onclick;
	}


	public void exportCSV(CSVWriter writer) throws IOException {
		writer.writeNext(result);
		writer.flush();
	}

	/**
	 * ëSäpêîéöÇîºäpÇ…ïœä∑ÇµÇ‹Ç∑ÅB
	 * 
	 * @param s
	 *            ïœä∑å≥ï∂éöóÒ
	 * @return ïœä∑å„ï∂éöóÒ
	 */
	private static String zenHan(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 'ÇO' && c <= 'ÇX') {
				sb.setCharAt(i, (char) (c - 'ÇO' + '0'));
			}
		}
		return sb.toString();
	}
}
