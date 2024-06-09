package bookmark;

public class BookmarkDTO {

	private int bookmark_id;
	private int group_id;
	private String bookmark_name;
	private String wifi_name;
	private String regist_date;
	
	public int getBookmark_id() {
		return bookmark_id;
	}
	public void setBookmark_id(int bookmark_id) {
		this.bookmark_id = bookmark_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getBookmark_name() {
		return bookmark_name;
	}
	public void setBookmark_name(String bookmark_name) {
		this.bookmark_name = bookmark_name;
	}
	public String getWifi_name() {
		return wifi_name;
	}
	public void setWifi_name(String wifi_name) {
		this.wifi_name = wifi_name;
	}
	public String getRegist_date() {
		return regist_date;
	}
	public void setRegist_date(String regist_date) {
		this.regist_date = regist_date;
	}
}
