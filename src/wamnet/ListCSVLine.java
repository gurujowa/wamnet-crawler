package wamnet;

public class ListCSVLine {

	private String name;
	private String zip_code;
	private String address;
	private String tel;
	private String fax;
	private String category;
	private String url;
	
	public ListCSVLine(String[] csv) {
		name = csv[0];
		zip_code = csv[1];
		address = csv[2];
		tel = csv[3];
		fax = csv[4];
		category = csv[5];
		url = csv[6];
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
