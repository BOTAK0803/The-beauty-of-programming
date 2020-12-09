// 3.11 程序改错
class Test{
	public static void main(String[] args) {
		/**
		基础问题：有一个已经排好序的数组，返回数组中最后一个目标元素的下标
			
		*/
		int[] arr = new int[]{1,2,3,3,4,5,5,5,5,6};
		System.out.println(binSearch(arr,0,arr.length-1,5));
		
	}
	public static int binSearch(int[] arr,int start,int end,int target){
		int left = start;
		int right = end;
		while(left<right){
			int mid = left + (right - left+1)/2;
			if(arr[mid] > target) right = mid -1;
			else if(arr[mid] == target) left = mid;
			else left = mid+1;
		}
		return left;
	}
}