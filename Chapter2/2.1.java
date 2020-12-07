// 求二进制中1的个数
// 对一个字节的变量，求其二进制表示中1的个数，要求算法的执行效率尽可能的高
class Test{
	public static void main(String[] args) {
		// 5 101
		System.out.println(numOfOne(5));
		System.out.println(numOfOne2(5));
		// 4 100 5: 101
		System.out.println(dis(4,5));
	}
	// myidea 方法1: %2
	public static int numOfOne(int n){
		int res = 0;
		while(n !=0){
			if(n%2==1) res++;
			n/=2;
		}
		return res;
	}
	// myidea 方法2: 使用位操作
	public static int numOfOne2(int n){
		int res = 0;
		while(n!=0){
			res++;
			n&=(n-1);
		}
		return res;
	}
	// myidea 方法3:使用分支操作，因为只有8位， 索性把0-255 都列举出来时间复杂度为O(1)
	// 另一个相关的问题，给定两个正整数（二进制表示）A和B，问把A变成B需要改变多少位？
	// myidea：将两个数字按照位异或，然后计算异或结果中1的数量
	public static int dis(int i,int j){
		int tmp = i^j;
		int res = 0;
		while(tmp!=0){
			res++;
			tmp&=(tmp-1);
		}
		return res;
	}
}
// 如果变量是32位的DWORD，你会使用上述哪一个算法，或者说是优化哪一个算法
// 若是我的话，会使用位操作来进行
