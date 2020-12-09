// 3.7 队列中取最大值操作问题
import java.util.*;
class Test{
	public static void main(String[] args) {
		/**
		基本问题 ：
			假设有这样一个拥有三个操作的队列：
				- EnQueue(v) : 将v加入队列中
				- DeQueue() : 使队列中的队首元素删除并返回此元素
				- MaxElement : 返回队列中的最大元素
			请设计一种数据结构和算法，让MaxElement操作的时间复杂度尽可能的低
		> 解法：
			- 解法1 ：采用一个辅助队列保存最大元素，或者遍历整个队列来寻找 时间复杂度$O(n)$
			- 解法2 ：采用最大堆,时间复杂度$O(\log_2n)$
			- 解法3 ：利用栈，因为pop和push都是在栈顶完成的，很容易维护整个栈中的最大值。时间复杂度$O(1)$
			
		*/
		// ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
		MaxQueue();
		push_back(1);
		push_back(2);
		System.out.println(max_value());
		pop_front();
		System.out.println(max_value());

		
	}
	static Queue<Integer> q;
    static Deque<Integer> d;

    public static void MaxQueue() {
        q = new LinkedList<Integer>();
        d = new LinkedList<Integer>();
    }
    
    public static int max_value() {
        if (d.isEmpty()) {
            return -1;
        }
        return d.peekFirst();
    }
    
    public static void push_back(int value) {
        while (!d.isEmpty() && d.peekLast() < value) {
            d.pollLast();
        }
        d.offerLast(value);
        q.offer(value);
    }
    
    public static int pop_front() {
        if (q.isEmpty()) {
            return -1;
        }
        int ans = q.poll();
        if (ans == d.peekFirst()) {
            d.pollFirst();
        }
        return ans;
    }

}