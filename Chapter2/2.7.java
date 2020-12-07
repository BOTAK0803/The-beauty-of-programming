// 2.7 最大公约数问题
class Test{
	public static void main(String[] args) {
		/**
		基础问题：写一个程序，求取两个正整数的最大公约数。如果两个正整数都很大，有什么简单的算法么？
		> 解法：
			- 解法1 ： 欧几里得的辗转相除法
			- 解法2 ： 将欧几里得中的除法转换成为减法
			- 解法3 ： 结合解法1的除法与解法2的减法
		*/
		int x = 42;
		int y = 30;
		// $$f(42,30) = f(30,12) = f(12,6) = f(6,0) = 6$$
		System.out.println(gcd1(x,y));
		// $$f(42,30) = f(12,30) = f(30,12) = f(18,12) = f(6,12) = f(12,6) = f(6,6) = f(6,0)$$
		System.out.println(gcd2(x,y));
		// 
		System.out.println(gcd3(x,y));
		
	}
	/**
	解法1:欧几里得的辗转相除法
	*/
	public static int gcd1(int x,int y){
		return y==0?x:gcd1(y,x%y);

	}
	/**
	解法2:
	解法1的辗转相除法中用到了取模运算，对于较大的整数而言，取模运算是非常昂贵的开销
	利用减法代替取模运算
	*/
	public static int gcd2(int x,int y){
		if(x<y) return gcd2(y,x);
		if(y==0) return x;
		else return gcd2(x-y,y);
	}
	/**
	解法3:
	解法1的问题在于利用了计算复杂的取模运算
	解法2的问题在于虽然将取模运算换成了减法运算，但是减法的迭代次数好多啊
	解法3想办法结合解法1的优点和解法2的优点
		思想：
		if x,y all even : f(x,y) = 2*f(x/2,y/2) = 2*f(x>>1,y>>1)
		if x is even && y is odd : f(x,y) = f(x/2,y) = f(x>>1,y)
		if x is odd && y is even : f(x,y) = f(x,y/2) = f(x,y>>1)
		if x,y all odd : f(x,y) = f(x,x-y);
	那么在$f(x,y) = f(x,x-y)$之后，$x-y$是个偶数，下一步一定会有除以2的操作

	*/

	public static int gcd3(int x,int y){
		if(x<y) return gcd3(y,x);
		if(y==0) return x;
		else{
			// 如果x是偶数
			if(x%2==0){
				// 如果y也是偶数
				if(y%2==0) return (gcd3(x>>1,y>>1)<<1);
				else return gcd3(x>>1,y);
			}else{
				if(y%2==0) return gcd3(x,y>>1);
				else return gcd3(y,x-y);
			}
		}
	}
}