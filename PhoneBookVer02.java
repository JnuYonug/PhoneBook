import java.util.Scanner;

public class PhoneBookVer02 {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("선택하세요. . .");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 프로그램 종료");
		System.out.print("선택 : ");
	}
	
	public static PhoneInfo readData() {
		String name, phoneNumber, birthday;
		
		System.out.print("이름 : "); name = sc.nextLine();
		System.out.print("전화번호 : "); phoneNumber = sc.nextLine();
		System.out.print("생년월일 : "); birthday = sc.nextLine();
		
		return new PhoneInfo(name, phoneNumber, birthday);
	}
	
	public static void main(String[] args) {
loop: while(true) {
			showMenu();
			int sel = sc.nextInt(); sc.nextLine();
			
			switch(sel)
			{
			case 1:
				PhoneInfo pInfo = readData();
				pInfo.showInfo();
				break;
			case 2:
				System.out.println("\n프로그램을 종료합니다.");
				break loop;
				
			default:
				System.out.println("\n다시 입력해주세요!");
			}
			
			System.out.println();
		}
	}
}
