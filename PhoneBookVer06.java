public class PhoneBookVer06 {
	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.getInstance();
		manager.restorePhoneBook();
		
loop:	while(true) {
			manager.showMenu();
			
			int sel = manager.sc.nextInt(); 
			System.out.println(manager.sc.nextLine());
			
			switch(MENU.intToMENU(sel))
			{
			case INPUT :
				manager.readData();
				break;
			case SEARCH :
				manager.searchData();
				break;
			case LIST:
				manager.showAll();
				break;
			case DELETE :
				manager.removeData();
				break;
			case EXIT :
				manager.savePhoneBook();
				break loop;
				
			default:
				System.out.println("�ٽ� �Է����ּ���!");
			}
			
			System.out.println();
		}
	
		System.out.println("���α׷��� ����˴ϴ�.\n");
	}
}
