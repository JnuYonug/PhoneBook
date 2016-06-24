public enum TYPE {
	USUAL, UNIV, COMP;
	
	private static final TYPE[] list = TYPE.values();
	
	public static TYPE intToTYPE(int n) {
		for(TYPE t: list) {
			if(t.ordinal()+1 == n) {
				return t;
			}
		}
		return null;
	}
}