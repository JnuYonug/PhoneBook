public class PhoneBookVer05 {
	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.getInstance();
		
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
			case DELETE :
				manager.removeData();
				break;
			case EXIT :
				break loop;
				
			default:
				System.out.println("다시 입력해주세요!");
			}
			
			System.out.println();
		}
	
		System.out.println("프로그램이 종료됩니다.\n");
	}
}
