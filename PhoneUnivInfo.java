public class PhoneUnivInfo extends PhoneInfo {
	private String major;
	private int year;
	
	public PhoneUnivInfo(String name, String phoneNumber, String major, int year) {
		super(name, phoneNumber);
		this.major = major;
		this.year = year;
	}
	
	public String getMajor() { return major; }
	public int getYear() { return year; }
	
	public void setMajor(String major) { this.major = major; }
	public void setYear(int year) { this.year = year; }
	
	public void showInfo() {
		super.showInfo();
		System.out.println("major : " + major);
		System.out.println("year : " + year);	 
	}
}
