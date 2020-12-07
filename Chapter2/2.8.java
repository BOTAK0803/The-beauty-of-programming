// 2.8 找到符合条件的整数
import java.math.BigInteger;
class Test{
	public static void main(String[] args) {
		// 基础问题：任意给定一个正整数N，求一个最小的正整数M(M>1)，使得N*M的十进制表示形式里只含有1和0
		/**
		>解法
			解法1 ： 暴力解决
		*/
		System.out.println(isOnlyHaveOneOrZero(10140101));
		// 110101 = 23 * 4787
		System.out.println(findNum1(23));
		System.out.println(findNum2(99));
		
	}
	// 判断某个数字十进制表示是否只有1和0
	public static boolean isOnlyHaveOneOrZero(int n){
		while(n!=0){
			if(n%10 != 0 && n%10 != 1) return false;
			n=n/10;
		}
		return true;
	}
	/**
	解法1 ： 暴力解法
	*/
	public static int findNum1(int N){
		for(int i = 1;i<Integer.MAX_VALUE/N;i++) if(isOnlyHaveOneOrZero(i*N)) return i;
		return 0;
	}
	/**
	解法2:
	*/
	public static BigInteger findNum2(int n) {
		// 用于记录余数的数组
        BigInteger []remain2Num = new BigInteger[n];
        remain2Num[1] = new BigInteger("1");
        BigInteger base = new BigInteger("10"), N = new BigInteger(String.valueOf(n));
        // core
        for (BigInteger now = new BigInteger("10"); ; now = now.multiply(base)) {
            int remain = now.remainder(N).intValue();
            if(remain2Num[remain] == null){
                remain2Num[remain] = now;
            }
            for (int i = 1; i < n; i++) {
                if(remain2Num[i] == null)
                    continue;
                int newRemain = (remain + i) % n;
                if(remain2Num[newRemain] == null && remain2Num[i].compareTo(now) < 0){
                    remain2Num[newRemain] = now.add(remain2Num[i]);
                }
            }
            if (remain2Num[0] != null) break;
        }
        return remain2Num[0].divide(N);
    }
	/**
	拓展问题
		1 对于任意的N，一定存在M，使得N*M的乘积的十进制表示只有0和1么？
		2 怎样找出满足题目要求的N和M，使得$N*M < 2^{16}$，且N+M最大？

	*/

}