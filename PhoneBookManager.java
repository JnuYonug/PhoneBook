import java.util.Scanner;

public class PhoneBookManager {
	public final Scanner sc = new Scanner(System.in);
	private final int MAX_CNT;
	
	private PhoneInfo[] arr;
	private int cnt = 0;
	
	public PhoneBookManager(int cap) {
		MAX_CNT = cap;
		arr = new PhoneInfo[cap];
	}

	
	
	public void showMenu() {
		System.out.println("�����ϼ��� . . .");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ������ �˻�");
		System.out.println("3. ������ ����");
		System.out.println("4. ���α׷� ����");
		System.out.print("���� : ");
	}
	
	public int searchIdx(String name) {
		for(int i=0; i<cnt; i++) {
			if(arr[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public void searchData() {
		System.out.println("������ �˻��� �����մϴ� . .");
	
		System.out.print("�̸� : "); String name = sc.nextLine();
		int idx = searchIdx(name);
		
		if(idx == -1) {
			System.out.println("���� �������Դϴ�!");
			return;
		} else {
			arr[idx].showInfo();
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.");
		}
	}
	
	public void removeData() {
		System.out.println("������ ������ �����մϴ� . .");
		
		System.out.print("�̸� : "); String name = sc.nextLine();
		int idx = searchIdx(name);
		
		if(idx == -1) {
			System.out.println("���� �������Դϴ�!");
			return;
		} else {
			cnt--;
			for(int i=idx; i<cnt; i++) {
				arr[i] = arr[i+1];
			}
			
			System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
		}
	}
	
	public void readData() {
		if(cnt == MAX_CNT) {
			System.out.println("������ ������ �����մϴ� . .");
			return;
		}
		
		String name, phoneNumber, birthday;
		
		System.out.println("������ �Է��� �����մϴ� . .");
		System.out.print("�̸� : "); name = sc.nextLine();
		System.out.print("��ȭ��ȣ : "); phoneNumber = sc.nextLine();
		System.out.print("������� : "); birthday = sc.nextLine();
		
		arr[cnt++] = new PhoneInfo(name, phoneNumber, birthday);
	
		System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�.");
	}
}
