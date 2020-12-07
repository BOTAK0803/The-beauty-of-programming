// 2.9 Fibonacci 数列
// import java.util.Math;
class Test{
	public static void main(String[] args) {
		/**
		## 基础问题
		F(n) = 
			0 , if n==0
			1 , if n==1
			F(n-1) + F(n-1) , if n>1
		
		> 解法
			- 解法1 ： 递归
			- 解法2 ： 求解通项公式
			- 解法3 ： 分治策略
			- 解法4 ： 动态规划
			*/
		/**
		## 拓展问题
			假设$A(0) = 1,A(1) = 2,A(2) = 2$，对于$n>2$,都有$A(k) = A(k-1) + A(k-2) + A(k-3)$
				1，对于任何一个n，如何计算出$A(n)$
				2，对于n非常大的状况，比如$n=2^{60}$，如何计算$A(n) mod N (M < 100000)呢？$
		*/
		System.out.println(Fib1(10));
		System.out.println((int)Fib2(10));
		System.out.println(Fib3(10));
		System.out.println(Fib4(10));
		System.out.println(Fib5(10));
	}
	/**
	解法1 ： 递归
	*/
	public static int Fib1(int n){
		if(n<=0) return 0;
		else if(n==1) return 1;
		else return Fib1(n-1) + Fib1(n-2);
	}
	/**
	解法2 ： 求解通项公式
	$$F(n) = F(n-1) + F(n-2)$$
	由此得到特征方程:
	$$x^2 = x + 1$$
	有根
	$$x_1 = \frac{1+\sqrt{5}}{2},x_2 = \frac{1-\sqrt{5}}{2}$$
	所以存在A,B使得
	$$F(n) = A * (\frac{1+\frac{5}}{2})^n + B*(\frac{1-\sqrt{5}}{2})^n$$
	代入$F(0) = 0,f(1) = 1$得$A=\frac{\sqrt{5}}{5},B=-\frac{\sqrt{5}}{5}$
	$$F(n) = \frac{\sqrt{5}}{5} * (\frac{1+\frac{5}}{2})^n - \frac{\sqrt{5}}{5} * (\frac{1-\sqrt{5}}{2})^n$$
	通过公式，我们可以在O(1)时间复杂度求得，但是引入了无理数，所以不能保证结果的精度
	*/
	public static double Fib2(int n){
		return Math.sqrt(5.0)/5.0*Math.pow((1.0+Math.sqrt(5.0))/2.0,n) - Math.sqrt(5.0)/5.0*Math.pow((1.0-Math.sqrt(5.0))/2.0,n);

	}
	/**
	解法3 ： 分治策略 矩阵
	*/
	public static int Fib3(int N){
        if (N <= 1) 
      		return N;
        int[][] A = new int[][]{{1, 1}, {1, 0}};
        matrixPower(A, N-1);

        return A[0][0];

	}

    public static void matrixPower(int[][] A, int N) {
        if (N <= 1) {
          return;
        }
        matrixPower(A, N/2);
        multiply(A, A);

        int[][] B = new int[][]{{1, 1}, {1, 0}};
        if (N%2 != 0) {
            multiply(A, B);
        }
    }

    public static void multiply(int[][] A, int[][] B) {
        int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];

        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }
	/**
	解法4 : 动态规划
	*/
	public static int Fib4(int n){
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		for(int i =2;i<= n;i++) dp[i] = dp[i-1] + dp[i-2];
		return dp[n];
	}
		/**
	解法4.2 : 动态规划优化
	*/
	public static int Fib5(int n){
		int a = 0;
		int b = 1;
		int c = 0;
		for(int i = 2;i<=n;i++){
			c=a+b;
			a = b;
			b=c;
		}
		return c;
	}

}