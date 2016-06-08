public class PhoneInfo {
	private String name;
	private String phoneNumber;
	private String birthday;
	
	public PhoneInfo(String name, String phoneNumber) { this(name, phoneNumber, null); }
	public PhoneInfo(String name, String phoneNumber, String birthday) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
	}
	
	public String getName() { return name; }
	public String getPhone() { return phoneNumber; }
	public String getBirth() { return birthday; }
	
	public void setName(String name) { this.name = name; }
	public void setPhone(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public void setBirth(String birthday) { this.birthday = birthday; }
	
	public void showInfo() {
		System.out.println("name : " + name);
		System.out.println("phone : " + phoneNumber);
		if(birthday != null) {
			System.out.println("birth : " + birthday);		 
		}
	}
}

