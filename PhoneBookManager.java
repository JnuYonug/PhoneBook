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
		System.out.println("선택하세요 . . .");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.print("선택 : ");
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
		System.out.println("데이터 검색을 시작합니다 . .");
	
		System.out.print("이름 : "); String name = sc.nextLine();
		int idx = searchIdx(name);
		
		if(idx == -1) {
			System.out.println("없는 데이터입니다!");
			return;
		} else {
			arr[idx].showInfo();
			System.out.println("데이터 검색이 완료되었습니다.");
		}
	}
	
	public void removeData() {
		System.out.println("데이터 삭제를 시작합니다 . .");
		
		System.out.print("이름 : "); String name = sc.nextLine();
		int idx = searchIdx(name);
		
		if(idx == -1) {
			System.out.println("없는 데이터입니다!");
			return;
		} else {
			cnt--;
			for(int i=idx; i<cnt; i++) {
				arr[i] = arr[i+1];
			}
			
			System.out.println("데이터 삭제가 완료되었습니다.");
		}
	}
	
	public void readData() {
		if(cnt == MAX_CNT) {
			System.out.println("저장할 공간이 부족합니다 . .");
			return;
		}
		
		String name, phoneNumber, major, company;
		int year, sel=1;		
		
		System.out.println("데이터 입력을 시작합니다 . .");
		do {
			if(sel != 1) {
				System.out.println("다시 입력해주세요!\n");
			}
			System.out.println("1. 일반, 2. 대학, 3. 회사");
			System.out.print("선택 >> ");
			
			sel = sc.nextInt(); sc.nextLine();
		} while(sel<1 || sel>3);
		
		System.out.print("이름 : "); name = sc.nextLine();
		System.out.print("전화번호 : "); phoneNumber = sc.nextLine();
		
		PhoneInfo tmp = null;
		switch(TYPE.intToTYPE(sel)) 
		{
		case USUAL :
			tmp = new PhoneInfo(name, phoneNumber);
			break;
		case UNIV :
			System.out.print("전공 : "); major = sc.nextLine();
			System.out.print("학년 : "); year = sc.nextInt();
			
			tmp = new PhoneUnivInfo(name, phoneNumber, major, year);
			break;
			
		case COMP :
			System.out.print("회사 : "); company = sc.nextLine();
			
			tmp = new PhoneCompanyInfo(name, phoneNumber, company);
			break;
		}
		
		arr[cnt++] = tmp;
			
		System.out.println("데이터 입력이 완료되었습니다.");
	}
}
