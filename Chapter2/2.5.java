import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// 2.5 寻找最大的K个数
class Test{
	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4,5,6,1,1};
		System.out.println(KthNum1(arr,2));
		System.out.println(KthNum4(arr,2));
	}
	/**
	## 基础问题：
	有很多个无序的数，我们姑且假定他们各不相等，怎么选出其中最大的K个数呢？
	> 解法
	- 解法 1 ： 排序 quickSort selectSort popSort 
		先排序，然后再从中选出K个元素。时间复杂度O(N*logN) + O(K) = O(N*logN)
		排序可以做一部分的优化，可以利用选择排序和交换排序，当进行到第K层循环的时候结束就行了，时间复杂度为O(N*K)
	- 解法 2 ： 快速排序优化

	- 解法 3 ： 二分搜索
	- 解法 4 ： heap priorityQueue
	- 解法 5 ： Hash

	*/

	/**
	解法 1 ： 排序 quickSort selectSort popSort 
	*/
	public static List<Integer> KthNum1(int[] arr, int k){
		List<Integer> res = new ArrayList<Integer>();
		Arrays.sort(arr);
		for(int i = arr.length - k;i<arr.length;i++) res.add(arr[i]);
		return res;
	}
	/**
	解法 2 : 快速排序优化
	快速排序而言：
		对于一个privot而言，经历了一次快速排序之后，小于privot的在privot的左边，大于privot的在privot的右边
		经历了一次排序之后，有两种可能性：
			左边元素的数目小于K，那么左边的元素全部都归属于答案，那么还需要快速排序privot右边的继续取
			左边的元素的数目大于K，那么直接取元素的K个
	*/
	/**
	解法 3 ： 二分搜索
	对于二分搜索而言：	
		
	*/
	/**
	解法 4 ： heap priorityQueue
	java中实现堆的方式是利用优先队列，一般是小顶堆
	Queue<Integer> queue = new PriorityQueue<Integer>();
	如果想用大顶堆，就需要重写PriorityQueue<>接口中的Comparator比较器，一般简单而言，用lambda来写
	Queue<Integer> queue = new PriorityQueue<Integer>((o1, o2) -> (o2 - o1));
	*/
	public static List<Integer> KthNum4(int[] arr,int k){
		// 创建一个小顶堆
		Queue<Integer> queue = new PriorityQueue<>();
		for(int i:arr){
			// 如果队列中的元素不足3个
			if(queue.size() < k) queue.offer(i);
			// 队列中的元素超过了三个
			// 判断当前元素是否大于小顶堆的最上面的元素
			if(i > queue.peek()){
				queue.poll();
				queue.offer(i);
			}
		}
		List<Integer> ans = new ArrayList<Integer>();
		for(int i:queue) ans.add(i);
		return ans;
	}
	/**
	方法5 : 利用hashMap
	*/

	/**
	## 拓展问题
	### 1 如果需要找出N个数中最大的K个不同的浮点数呢？
	> answer :感觉是一样的
	### 2 如果是找第k到m大的数呢？
	> 利用堆简单一点
	### 3 在搜索引擎中，网络上的每一个页面都有“权威性”，例如PageRank。如果我们需要寻找权重最大的K个网页，
		而网页的权重会不断的更新，那么算法要如何变动以达到快速更新？并及时返回权重最大的K个网页？
	>利用堆
	### 4 在实际应用中，还有一个精确度的问题，我们可能不需要严格意义上的最大的K个元素，在边界位置允许出现一些误差。
	> 

	*/

}