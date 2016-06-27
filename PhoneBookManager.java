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
		System.out.println("�����ϼ��� . . .");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ������ �˻�");
		System.out.println("3. ������ ���");
		System.out.println("4. ������ ����");
		System.out.println("5. ����(������ ����)");
		System.out.print("���� : ");
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
	public void searchData() {
		System.out.println("������ �˻��� �����մϴ� . .");
	
		System.out.print("�̸� : "); String name = sc.nextLine();
		int idx = searchIdx(name, 0);

		if(idx == -1) {
			System.out.println("���� �������Դϴ�!");
			return;
		} else {
			System.out.printf("%s\t %s\t %s\t\t %s\t %s\t %s \n", "kind", "name", "phone", "major", "year", "company");
			
			do {
				arr[idx].showInfo();
				idx = searchIdx(name, idx+1);
			
				System.out.println();
			} while(idx != -1);

			System.out.println("\n������ �˻��� �Ϸ�Ǿ����ϴ�.");
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
		System.out.println("������ ������ �����մϴ� . .");
		
		System.out.print("�̸� : "); String name = sc.nextLine();
		int idx = searchIdx(name, 0);
		
		if(idx == -1) {
			System.out.println("���� �������Դϴ�!");
			return;
		} else {
			do {   // it should be fixed! :(
				cnt--;
				for(int i=idx; i<cnt; i++) {
					arr[i] = arr[i+1];
				}
				
				idx = searchIdx(name, idx+1);
			} while(idx != -1);
	
			System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
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
