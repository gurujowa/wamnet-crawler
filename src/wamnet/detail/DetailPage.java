package wamnet.detail;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class DetailPage {
	
	private WebDriver driver;
	private LinkedHashMap<String,String> detail;
	
	private String company_tab = "//*[@id=\"tab-1-li\"]/span/a";
	private String place_tab = "//*[@id=\"tab-2-li\"]/span/a";
	private String staff_tab = "//*[@id=\"tab-3-li\"]/span/a";

	private String leader_path = "//*[@diffid=\"diff-c185\"]";
	private String title_path = "//*[@diffid=\"diff-c186\"]";
	private String start_path = "//*[@diffid=\"diff-c187\"]";
	private String url_path = "//*[@diffid=\"diff-c183\"]/a";
	private String url_path2 = "//*[@diffid=\"diff-c183\"]";

	private String c_category_path = "//*[@diffid=\"diff-c1\"]";
	private String c_name_path = "//*[@diffid=\"diff-c4\"]";
	private String c_address_path = "//*[@diffid=\"diff-c6\"]";
	private String c_tel_path = "//*[@diffid=\"diff-c7\"]";
	private String c_fax_path = "//*[@diffid=\"diff-c8\"]";
	private String c_person_path = "//*[@diffid=\"diff-c11\"]";
	private String c_title_path = "//*[@diffid=\"diff-c12\"]";

	private String man_path = "//*[@diffid=\"diff-c213\"]";
	private String woman_path = "//*[@diffid=\"diff-c214\"]";

	
	public DetailPage(WebDriver dr) {
		this.driver = dr;
		detail = new LinkedHashMap<String, String>();
	}

	public boolean parse(String url) {
		if (!accessPlaceTab()) {
			System.err.println("Cant find URL. URL = " + url);
			return false;
		}
		
		parsePlace();
		driver.findElement((By.xpath(company_tab))).click();
		parseCompany();
		driver.findElement((By.xpath(staff_tab))).click();
		parseStaff();
		return true;
	}
	

	public boolean isPageFound() {
		if (driver.findElement(By.className("error")).getText() == "�Y������y�[�W��������܂���ł����B") {
			return true;
		} else {
			return false;
		}
	}
	
	public LinkedHashMap<String, String> getMap() {
		return this.detail;
	}
	
	public DetailPage accessURL(String url) throws IOException, InterruptedException {
		driver.get(url);

		for (int i = 0;; i++) {
		    try {
		    	parse(url);
		        break;
		    } catch (UnreachableBrowserException e) {
		        if (i > 10) {
		            throw e;
		        }
		    } catch (NoSuchElementException e) {
		    	System.out.println("No Such Element. URL = " + url);
		    	throw e;
		    	
		    }
		}
		return this;
	}
	
	private void parseStaff() {
    	detail.put("�������i�j���j",driver.findElement((By.xpath(man_path))).getText());
    	detail.put("�������i�����j",driver.findElement((By.xpath(woman_path))).getText());
    	detail.put("�K������i��΁E��]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c195\"]"))).getText());
    	detail.put("�K������i��΁E���]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c196\"]"))).getText());
    	detail.put("�K������i���΁E��]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c197\"]"))).getText());
    	detail.put("�K������i���΁E���]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c198\"]"))).getText());
    	detail.put("�������i��΁E��]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c204\"]"))).getText());
    	detail.put("�������i��΁E���]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c205\"]"))).getText());
    	detail.put("�������i���΁E��]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c206\"]"))).getText());
    	detail.put("�������i���΁E���]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c207\"]"))).getText());
    	detail.put("���̑��i��΁E��]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c209\"]"))).getText());
    	detail.put("���̑��i��΁E���]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c210\"]"))).getText());
    	detail.put("���̑��i���΁E��]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c211\"]"))).getText());
    	detail.put("���̑��i���΁E���]�j",driver.findElement((By.xpath("//*[@diffid=\"diff-c212\"]"))).getText());
	}
	
	private void parseCompany() {
    	detail.put("�@�l�̎��",driver.findElement((By.xpath(c_category_path))).getText());
    	detail.put("�@�l��",driver.findElement((By.xpath(c_name_path))).getText());
    	detail.put("�@�l�̏Z��",driver.findElement((By.xpath(c_address_path))).getText());
    	detail.put("�@�l�̓d�b�ԍ�",driver.findElement((By.xpath(c_tel_path))).getText());
    	detail.put("�@�l��FAX",driver.findElement((By.xpath(c_fax_path))).getText());
    	detail.put("�@�l��\��",driver.findElement((By.xpath(c_person_path))).getText());
    	detail.put("�@�l�E��",driver.findElement((By.xpath(c_title_path))).getText());
		
	}

	private void parsePlace() {
    	detail.put("�Ǘ���", driver.findElement((By.xpath(leader_path))).getText());
    	detail.put("��E",driver.findElement((By.xpath(title_path))).getText());
    	detail.put("���ƊJ�n��",driver.findElement((By.xpath(start_path))).getText());
    	detail.put("���URL",parseURL());    	
	}
	
	private String parseURL() {
		List<WebElement> el = driver.findElements(By.xpath(url_path));
		if (el.isEmpty()) {
		  return  driver.findElement(By.xpath(url_path2)).getAttribute("href");
		} else {
  		  return el.get(0).getAttribute("href");
		}
		
	}
	
	private boolean accessPlaceTab() {
		try {
			driver.findElement((By.xpath(place_tab))).click();
			return true;
		} catch(NoSuchElementException e) {
			return false;
		}
	}

}
