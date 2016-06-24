import java.util.Scanner;

public class PhoneBookManager {
	private static PhoneBookManager instance = null; 
	
	public final Scanner sc = new Scanner(System.in);
	private final int MAX_CNT = 100;
	
	private final PhoneInfo[] arr;
	private int cnt = 0;
	
	private PhoneBookManager() {
		arr = new PhoneInfo[100];
		instance = this;
	}
	
	public static PhoneBookManager getInstance() {
		if(instance == null) {
			instance = new PhoneBookManager();
		}
		return instance;
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
		
		String name, phoneNumber, major, company;
		int year, sel=1;		
		
		System.out.println("������ �Է��� �����մϴ� . .");
		do {
			if(sel != 1) {
				System.out.println("�ٽ� �Է����ּ���!\n");
			}
			System.out.println("1. �Ϲ�, 2. ����, 3. ȸ��");
			System.out.print("���� >> ");
			
			sel = sc.nextInt(); sc.nextLine();
		} while(sel<1 || sel>3);
		
		System.out.print("�̸� : "); name = sc.nextLine();
		System.out.print("��ȭ��ȣ : "); phoneNumber = sc.nextLine();
		
		PhoneInfo tmp = null;
		switch(TYPE.intToTYPE(sel)) 
		{
		case USUAL :
			tmp = new PhoneInfo(name, phoneNumber);
			break;
		case UNIV :
			System.out.print("���� : "); major = sc.nextLine();
			System.out.print("�г� : "); year = sc.nextInt();
			
			tmp = new PhoneUnivInfo(name, phoneNumber, major, year);
			break;
			
		case COMP :
			System.out.print("ȸ�� : "); company = sc.nextLine();
			
			tmp = new PhoneCompanyInfo(name, phoneNumber, company);
			break;
		}
		
		arr[cnt++] = tmp;
			
		System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�.");
	}
}
