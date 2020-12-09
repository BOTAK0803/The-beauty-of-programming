class Test{
	public static void main(String[] args) {
		String s1 = "AABBCD";
		String s2 = "CDAA";
		System.out.println(test(s1,s2));
		
	}
	public static boolean test(String s1,String s2){
		String s = s1+s1;
		for(int i = 0;i<s.length() - s2.length();i++){
			if(s.substring(i,i+s2.length()).equals(s2)) return true;
		}
		return false;
	}
}