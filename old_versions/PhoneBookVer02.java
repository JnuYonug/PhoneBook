import java.util.Scanner;

public class PhoneBookVer02 {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("�����ϼ���. . .");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ���α׷� ����");
		System.out.print("���� : ");
	}
	
	public static PhoneInfo readData() {
		String name, phoneNumber, birthday;
		
		System.out.print("�̸� : "); name = sc.nextLine();
		System.out.print("��ȭ��ȣ : "); phoneNumber = sc.nextLine();
		System.out.print("������� : "); birthday = sc.nextLine();
		
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
				System.out.println("\n���α׷��� �����մϴ�.");
				break loop;
				
			default:
				System.out.println("\n�ٽ� �Է����ּ���!");
			}
			
			System.out.println();
		}
	}
}
