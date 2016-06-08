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
		
		String name, phoneNumber, birthday;
		
		System.out.println("데이터 입력을 시작합니다 . .");
		System.out.print("이름 : "); name = sc.nextLine();
		System.out.print("전화번호 : "); phoneNumber = sc.nextLine();
		System.out.print("생년월일 : "); birthday = sc.nextLine();
		
		arr[cnt++] = new PhoneInfo(name, phoneNumber, birthday);
	
		System.out.println("데이터 입력이 완료되었습니다.");
	}
}
