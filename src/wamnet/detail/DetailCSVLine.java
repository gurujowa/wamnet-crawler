package wamnet.detail;


public class DetailCSVLine {
/*		
	private LinkedHashMap<String, String> map;

	
	public DetailCSVLine(ListCSVLine line) {
		map = new LinkedHashMap<String, String>();

		map.put("���Ə���", line.getName());
		map.put("�X�֔ԍ�", line.getZip_code());
		map.put("�Z��", line.getAddress());
		map.put("�J�e�S��", line.getCategory());
		map.put("�d�b�ԍ�", line.getTel());
		map.put("FAX", line.getFax());
	}
	
	
	public String[] header() {
		return map.keySet().toArray(new String[0]);
	}

    public void add(String key, String value) {
    	map.put(key,value);
    }
    
    public HashMap<String, String> map() {
    	return this.map;
    }
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this.map.toString());
	}

	public void writeLine(CSVWriter writer) throws IOException {
		String[] str = map.values().toArray(new String[0]);
    	writer.writeNext(str);
    	writer.flush();
	}*/

}
