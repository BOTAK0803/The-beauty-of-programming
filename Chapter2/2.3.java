// 2.3 寻找发帖水王
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
class Test{
	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4,5,4,4,4,4,4,4};
		System.out.println(mainNum(arr));
		System.out.println(mainNum2(arr));
		int[] arr2 = new int[]{1,1,2,2,2,3,3,3,4,4,4};
		System.out.println(mainNums(arr2));
		System.out.println(mainNums2(arr2));
		
	}
	/**
	基础问题：就是找到一个数组中超过一半的数字,
	*/
	// 解法1 vote 投票法
	public static int mainNum(int[] arr){
		int vote = 0;
		int res = 0;
		for(int i = 0;i<arr.length;i++){
			// 如果当前的票数为0，表示没有候选人
			if(vote == 0){
				vote++;
				res = arr[i];
			}
			// 如果有票数
			else{
				// 如果当前元素就是候选人
				if(arr[i] == res) vote++;
				// 如果当前元素不是候选人
				else vote--;
			}
		}
		return res;
	}
	// 解法2 sort 排序法
	public static int mainNum2(int[] arr){
		Arrays.sort(arr);
		return arr[arr.length/2];
	}
	/**
	拓展问题：
	超级水王没有了，统计结果表明有三个发帖很多的id，发帖的数目均超过了总数目的1/4 ， 您让快速的找到他们的id么？
	*/
	// 解法1 当然是hashmap了，用hashmap记录，然后遍历hashmap的value，看哪一个的value大于arr.length/4 ，记录相应的key即可
	public static List<Integer> mainNums(int[] arr){
		Map<Integer,Integer> record = new HashMap<>();
		for(int num:arr){
			if(!record.containsKey(num)) record.put(num,0);
			record.put(num,record.get(num)+1);
		}
		List<Integer> res = new ArrayList<>();
		for(int key:record.keySet()) if(record.get(key) > arr.length/4) res.add(key);
		return res;
	} 
	// 解法2 当然还是使用投票法了 vote
	public static List<Integer> mainNums2(int[] arr){
		// 创建一个数组用于记录票数
		int[] votes = new int[3];
		// 创建一个数组用于记录结果
		int[] res = new int[3];
		for(int num:arr){
			// flag 表示当前的元素是否被利用
			boolean flag = false;
			// 如果当前遍历的元素已经在res里面了
			for(int i = 0;i<3;i++){
				if(num == res[i]){
					votes[i]++;
					flag = true;
					break;
				}

			}
			// 如果当前的元素不再res里面,votes中三个位置还有0，就将res这个位置用当前遍历的元素填充
			for(int i =0;i<3;i++){
				if(votes[i] == 0 && !flag){
					votes[i]++;
					res[i] = num;
					flag = true;
					break;
				}
			}
			if(!flag){
				if(num == arr[0]){
					votes[0]++;
				}
				else if(num == arr[1]){
					votes[1]++;
				}
				else if(num == arr[2]){
					votes[2]++;

				}
				else for(int i =0;i<3;i++) votes[i]--;
			}
		}
		List<Integer> ans = new ArrayList<>();
		for(int num:res) ans.add(num);
		return ans; 


	}

}