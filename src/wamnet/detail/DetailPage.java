package wamnet.detail;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

	public void parse() {
		driver.findElement((By.xpath(place_tab))).click();
		parsePlace();
		driver.findElement((By.xpath(company_tab))).click();
		parseCompany();
		driver.findElement((By.xpath(staff_tab))).click();
		parseStaff();
		String name = detail.get("���Ə���");
        System.out.println(name);
	}
	
/*	public void writeHeader(CSVWriter writer) {
		parse();
		String[] str = detail.header();
    	writer.writeNext(str);
	}
	*/
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
	
	public void accessURL(String url) throws IOException, InterruptedException {
		driver.get(url);

		for (int i = 0;; i++) {
		    try {
		    	parse();
		        break;
		    } catch (UnreachableBrowserException e) {
		        if (i > 10) {
		            throw e;
		        }
		    }
		    Thread.sleep(1000);
		}
		
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
    	detail.put("���URL",driver.findElement((By.xpath(url_path))).getText());    	
	}

}
