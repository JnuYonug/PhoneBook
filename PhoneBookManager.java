import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class PhoneBookManager {
	private static PhoneBookManager instance = null; 
	
	public final Scanner sc = new Scanner(System.in);
	private final int MAX_CNT = 100;
	
	private final PhoneInfo[] arr;   // should be changed to ArrayList
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
		System.out.println("3. 데이터 출력");
		System.out.println("4. 데이터 삭제");
		System.out.println("5. 종료(데이터 저장)");
		System.out.print("선택 : ");
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
	public void searchData() {
		System.out.println("데이터 검색을 시작합니다 . .");
	
		System.out.print("이름 : "); String name = sc.nextLine();
		int idx = searchIdx(name, 0);

		if(idx == -1) {
			System.out.println("없는 데이터입니다!");
			return;
		} else {
			System.out.printf("%s\t %s\t %s\t\t %s\t %s\t %s \n", "kind", "name", "phone", "major", "year", "company");
			
			do {
				arr[idx].showInfo();
				idx = searchIdx(name, idx+1);
			
				System.out.println();
			} while(idx != -1);

			System.out.println("\n데이터 검색이 완료되었습니다.");
		}
	}
	public void showAll() {
		System.out.printf("%s\t %s\t %s\t\t %s\t %s\t %s \n", "kind", "name", "phone", "major", "year", "company");
		for(int i=0; i<cnt; i++) {
			arr[i].showInfo();
			System.out.println();
		}
	}
	public void removeData() {
		System.out.println("데이터 삭제를 시작합니다 . .");
		
		System.out.print("이름 : "); String name = sc.nextLine();
		int idx = searchIdx(name, 0);
		
		if(idx == -1) {
			System.out.println("없는 데이터입니다!");
			return;
		} else {
			do {   // it should be fixed! :(
				cnt--;
				for(int i=idx; i<cnt; i++) {
					arr[i] = arr[i+1];
				}
				
				idx = searchIdx(name, idx+1);
			} while(idx != -1);
	
			System.out.println("데이터 삭제가 완료되었습니다.");
		}
	}	

	public void savePhoneBook() {
		try {
			FileOutputStream fout = new FileOutputStream(".encrypted");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream objOut = new ObjectOutputStream(bout);
		
			objOut.writeInt(cnt);
			for(int i=0; i<cnt; i++) {
				//arr[i].encryptAll();
				objOut.writeObject(arr[i]);
			}
			objOut.close();
		
		} catch(IOException ex) {
			// ex.printStackTrace();
			System.out.println("Error occured!!!");
			System.exit(4);
		}
		
		System.out.println("=== Decrypt Keys ===");
		System.out.println("key1 : " + RSACrypto.getPrivateKey());
		System.out.println("key2 : " + RSACrypto.getPublicKey1());
	}
	public void restorePhoneBook() {
		File encryptedFile = new File(".encrypted");
		if(!encryptedFile.exists()) {
			return;
		}
		
		try {
			FileInputStream fin = new FileInputStream(encryptedFile);
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream objIn = new ObjectInputStream(bin);
			
			this.cnt =objIn.readInt();
			
			System.out.print("Key1 : ");
			BigInteger key1 = new BigInteger(sc.nextLine());
			
			System.out.print("Key2 : ");
			BigInteger key2 = new BigInteger(sc.nextLine());
			
			for(int i=0; i<cnt; i++) {
				arr[i] = (PhoneInfo)objIn.readObject();
				arr[i].decryptAll(key1, key2);
			}
			objIn.close();
		
		} catch(IOException e) {
			// e.printStackTrace();
			System.out.println("Error ocurred!!!");
			System.exit(5);
		} catch(ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Error occured!!!");
			System.exit(6);
		}
	}
	
	public int searchIdx(String name, int idx) {
		for(int i=idx; i<cnt; i++) {
			if(arr[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
