// 1 给定一个正整数N，那么N的阶乘N! 末尾有多少个0呢？
// 2 求N!的二进制表示中最低位1的位置
class Test{
	public static void main(String[] args) {
		System.out.println(zeroOfN(50));
		System.out.println(zeroOfN2(50));
		System.out.println(posOfN(20));
		System.out.println(posOfN2(20));
		// true
		System.out.println(is2(2));
		
	}
	// 问题1 解法1，暴力将N的阶乘求出来然后再进行计算，但是这种情况很有可能会出现溢出的情况，是非常不建议使用的。
	// 问题1 解法2 质因数分解方法
	// 在介绍解法2之前，先介绍质因数分解
	/**
	合数：合数是指在大于1的整数中除了能被1和本身整除外，还能被其他数（0除外）整除的数。
	质数：质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。
	质因数：每个合数都可以写成几个质数相乘的形式，这几个质数就都叫做这个合数的质因数。
	质因数分解：正整数的因数分解可将正整数表示为一连串的质因子相乘，质因子如重复可以指数表示。根据算术基本定理，任何正整数皆有独一无二的质因子分解式。
	*/
	/** 质因数分解方法
	首先考虑$N! = K * 10 ^ M$,而且K不能被10整除，那么末尾就有M个0，
	继续分析，$N! = (2^x) * (3^y) * (5^z) * .... $由于$10 = 2 * 5$ 所以M只与x，y有关，$M = \min{x,y}$,因为被2整除比被5整除的数高得多，所以简化称为$M = z$

	*/
	public static int zeroOfN(int n){
		int res = 0;
		for(int i = 1;i<=n;i++){
			int j = i;
			while(j%5 == 0){
				res++;
				j/=5;
			}
		}
		return res;
	}
	// 问题1 解法3
	/**
	上述的z可以表示为$z = [n/5] +[n/5^2] +[n/5^3]+...$
	*/
	public static int zeroOfN2(int n){
		int res = 0;
		while(n != 0){
			res+=n/5;
			n/=5;
		}
		return res;

	}
	// 问题2 解法1 
	/**
	这个问题就相当于求N!中含有质因数2的个数，即答案等于N!含有质因数2的个数+1
	N!中含有质因数2的个数等于$N/2 + N/4 + N/8 + N/16 + ...$
	*/
	public static int posOfN(int n){
		int res = 0;
		while(n != 0){
			n>>=1;
			res+=n;
		}
		return res;

	}
	// 问题2 解法2
	/**
	N!含有质因数2的数目，还等于N减去N的二进制表示中1的数目
	*/
	public static int posOfN2(int N){
		int t = 0;
		int tmp = N;
		while(tmp != 0){
			t++;
			tmp&=(tmp-1);
		}
		return N-t;
	}
	// 拓展问题：给定一个整数，判断他是否是2的幂
	public static boolean is2(int n){
		return (n>0) &&((n&(n-1)) == 0);
	}
}
