// 2.4 1的数目
class Test{
	public static void main(String[] args) {
		/**
		基础问题：给一个十进制的正整数N，写下从1开始，到N的所有整数，然后数一下其中出现的所有1的个数
		1 写一个函数$f(N)$， 返回1到N之间出现的1的个数，比如$f(12) = 5$
		2 满足条件$f(N) = N$的最大的N是多少？
		*/


		System.out.println(calcNumOfOne(120));
		// for(int i = 0;i<120;i++) System.out.println(calcNumOfOne2(i));
		// 101
		// 0*2^0 + 1 + 0 + 1*2^0 + 0 +1 + 0*2^1 + 1 + 0 = 1 + 1 + 1 + 1 = 4
		System.out.println(calcNumOfOne3(12));

		
	}
	/** 基础问题1
		解法1 暴力求解 直接遍历即可
	*/  
	public static int calcNumOfOne(int n){
		int res = 0;
		for(int i = 0;i<=n;i++){
			int j = i;
			while(j !=0){
				if(j%10 == 1) res++;
				j/=10;
			}
		}
		return res;
	}
	/** 基础问题
		解法2 
		通过分析小于n的数在每一位上可能出现1的次数之和
		分析过程：
			先看只有1位数字的情况，只有1才会出现1个1
				$$if 0 < N < 10 , f(N) = 1 $$
			再看2位数的情况
				1出现的次数等于个位出现1的个数+十位出现1的个数
					13 = 2 （1，11） + 4 （10，11，12，13） = 6
					23 = 3（1，11，21） + 10（10，11，12，13，14，15，16，17，18，19）= 13
					33 = 4（1，11，21，31）+ 10（10，11，12，13，14，15，16，17，18，19）= 14
					....
					93 = 10 （1，11，21，31，41，51，61，71，81，91）+ 10（10，11，12，13，14，15，16，17，18，19） = 20
			再看3位数的情况：
				1出现的次数 = 个位上出现1的次数+十位上出现1的个数+百位上出现1的个数
					n =123
						个位上出现1的个数：13 （1，11，21，31，41，51，61，71，81，91，101，111，121）
						十位上出现1的个数：20（10～19，110～119）
						百位上出现1的个数：24（100～123）
			分析：
			假设N=abcde
				如果百位上的数字是0，那么百位上出现1的次数由高位决定 b*100
				如果百位上的数字是1，那么百位上出现1的次数由低位与高位共同决定 b*100 + de+1
				如果百位上的数字>1，那么百位上出现1的次数由高位决定 (b+1）*100
	*/
	public static int calcNumOfOne2(int n){
		int res = 0;
		// 表示现在遍历到哪一位
		int iFactor = 1;
		// 表示比当前小的位数的数字
		int iLowerNum = 0;
		// 表示当前位的数字
		int iCurrNum = 0;
		// 表示比当前大的位数的数字
		int iHighNum = 0;
		while(n/iFactor != 0){
			iLowerNum = n - (n/iFactor) * iFactor;
			iCurrNum = (n/iFactor)%10;
			iHighNum = n/(iFactor*10);
			switch(iCurrNum){
				case 0:
					res+=iHighNum * iFactor;
					break;
				case 1:
					res+=iHighNum * iFactor + iLowerNum + 1;
					break;
				default:
					res+=(iHighNum+1)*iFactor;
					break;
			}
			iFactor*=10;
		}
		if(res == n) System.out.print(res +" .. ");

		return res;
	}
	/**
	基础问题2:满足条件$f(N) = N$的最大的N是多少？
	$$N = N^{11} - 1$$
	*/
	/**
	拓展问题：
	对于二进制而言：每一位出现1的次数 = $high * 2^{i-1} + num_i + low$
	每一位出现1的次数=高位乘以上面的因子 + 该位的数字（1/0）+低位的数字（1/0） 
	*/
	public static String calcNumOfOne3(int n){
		int res = 0;
		// 表示现在遍历到哪一位
		int iFactor = 1;
		// 表示比当前小的位数的数字
		int iLowerNum = 0;
		// 表示当前位的数字
		int iCurrNum = 0;
		// 表示比当前大的位数的数字
		int iHighNum = 0;
		while(n/iFactor != 0){
			iLowerNum = n - (n/iFactor) * iFactor;
			iCurrNum = (n/iFactor)%10;
			iHighNum = n/(iFactor*10);
			switch(iCurrNum){
				case 0:
					res+=iHighNum * iFactor;
					break;
				case 1:
					res+=iHighNum * iFactor + iLowerNum + 1;
					break;
				default:
					res+=(iHighNum+1)*iFactor;
					break;
			}
			iFactor*=10;
		}
		String s = Integer.toBinaryString(res);
		return s;
	}

}