public class PhoneBookVer05 {
	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.getInstance();
		
loop:	while(true) {
			manager.showMenu();
			
			int sel = manager.sc.nextInt(); 
			System.out.println(manager.sc.nextLine());
			
			switch(sel)
			{
			case Menu.INPUT :
				manager.readData();
				break;
			case Menu.SEARCH :
				manager.searchData();
				break;
			case Menu.DELETE :
				manager.removeData();
				break;
			case Menu.EXIT :
				break loop;
				
			default:
				System.out.println("�ٽ� �Է����ּ���!");
			}
			
			System.out.println();
		}
	
		System.out.println("���α׷��� ����˴ϴ�.\n");
	}
}
