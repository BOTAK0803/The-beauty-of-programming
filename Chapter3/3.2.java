import java.util.*;
import java.util.Set.*;
class Test{
	public static void main(String[] args) {
		// 自定义辞典
		Set<String> dic = new HashSet<String>();
		dic.add("macbookair");
		dic.add("macbookpro");
		dic.add("macbookmin");
		dic.add("abcdefghij");		
		char[][] c = new char[][]{{' ',' ',' ',' '},{' ',' ',' ',' '},{'a','b','c',' '},{'d','e','f',' '},{'g','h','i',' '},{'j','k','l',' '},{'m','n','o',' '},{'p','q','r','s'},{'t','u','v',' '},{'w','x','y','z'}};

		String s = "";
		String num = "";
		dfs(s,num,c,dic);
	}
	/**
	*/
	public static void dfs(String s,String num,char[][] c,Set<String> dic){
		// System.out.println(s);
		// System.out.println("---------");
		if(s.length() > 10) return;
		if(s.length() == 10){
			if(dic.contains(s)) {
				System.out.println(s);
				System.out.println(num);
			}
			return;
		}
		for(int i = 0;i<c.length;i++){
			for(int j = 0;j<c[i].length;j++){
				if(c[i][j] == ' ') continue;
				s += c[i][j];
				num += i;
				
				dfs(s,num,c,dic);
				s.substring(0,s.length()-1);
				num.substring(0,num.length()-1);

			}

		}
	}
}