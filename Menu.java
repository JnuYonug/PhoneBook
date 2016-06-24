public enum MENU {
	INPUT, SEARCH, DELETE, EXIT;
	
	private static final MENU[] list = MENU.values();
	
	public static MENU intToMENU(int n) {
		for(MENU m: list) {
			if(m.ordinal()+1 == n) {
				return m;
			}
		}
		return null;
	}
}