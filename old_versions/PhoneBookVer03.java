public class PhoneBookVer03 {
	public static void main(String[] args) {
		PhoneBookManager manager = new PhoneBookManager(100);
		
loop:	while(true) {
			manager.showMenu();
			
			int sel = manager.sc.nextInt(); 
			System.out.println(manager.sc.nextLine());
			
			switch(sel)
			{
			case 1:
				manager.readData();
				break;
			case 2:
				manager.searchData();
				break;
			case 3:
				manager.removeData();
				break;
			case 4:
				break loop;
				
			default:
				System.out.println("�ٽ� �Է����ּ���!");
			}
			
			System.out.println();
		}
	
		System.out.println("���α׷��� ����˴ϴ�.\n");
	}
}
