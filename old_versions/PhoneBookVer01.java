
public class PhoneBookVer01 {
	public static void main(String[] args) {
		PhoneInfo pInfo1 = new PhoneInfo("김준영", "010-8976-8382", "990420");
		PhoneInfo pInfo2 = new PhoneInfo("임준오", "010-1234-5678");
	
		pInfo1.showInfo(); 
		System.out.println();
		
		pInfo2.showInfo();
	}
}
