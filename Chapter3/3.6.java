// 3.6 编程判断两个链表是否相交
import java.util.HashSet;
import java.util.*;
class Test{
	/**
	基本问题：给出两个单向链表的头指针，比如h1，h2，判断这两个链表是否相交。这里为了简化问题，我们
		假设两个链表均不带有环。
	解法：
		- 解法1 : 直观的想法,暴力枚举。
		- 解法2 : 采用计数的方法,将链表1的所有节点的hashcode放在一个hashset集合中,hashset查找是O(1)的
		- 解法3 : 将问题转换成为是否有环，将h2接在h1的后面.
		- 解法4 : 解法4 ： 如果相交最后的节点一定是相等的
	*/
	/**
	节点类
	*/
	static class ListNode{
		int val;
		ListNode next;
		ListNode(){}
		ListNode(int val){this.val=val;}
		ListNode(int val,ListNode next){this.val=val;this.next = next;}
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		ListNode h1 = new ListNode(8);
		ListNode h2 = new ListNode(7);
		ListNode h3 = new ListNode(6);
		h1.next = h2;
		h2.next = h3;
		h3.next = l4;
		print(l1);
		print(h1);
		// System.out.println(isConnect1(l1,h1));
		// System.out.println(isConnect2(l1,h1));
		// System.out.println(isConnect3(l1,h1));
		//System.out.println(isConnect4(l1,h1));
		System.out.println(getConnectNode(l1,h1));
	}
	public static void print(ListNode head){
		while(head!=null){
			System.out.print(head.val+"-->");
			head=head.next;
		} 
		System.out.print("null");
		System.out.println();
	}
	/**
	解法1:直观的想法,暴力枚举。
	*/
	public static boolean isConnect1(ListNode h1,ListNode h2){
		if(h1==null || h2 ==null) return false;
		ListNode p = h1;
		while(p!=null){
			ListNode q = h2;
			while(q!=null){
				if(p.val == q.val) return true;
				q=q.next;
			}
			p=p.next;
		}
		return false;
	}
	/**
	解法2 ： 采用计数的方法,将链表1的所有节点的hashcode放在一个hashset集合中
	hashset查找是O(1)的
	*/

	public static boolean isConnect2(ListNode h1,ListNode h2){
		if(h1 == null || h2 == null) return false;
		HashSet<Integer> set = new HashSet<>();
		ListNode p = h1;
		for(;p!=null;p=p.next) set.add(p.hashCode());
		ListNode q = h2;
		while(q!=null){
			if(set.contains(q.hashCode())) return true;
			q=q.next;
		}
		return false;
	}
	/**
	解法3 ： 将问题转换成为是否有环，将h2接在h1的后面.
	*/

	public static boolean isConnect3(ListNode h1,ListNode h2){
		if(h1 == null || h2 == null) return false;
		ListNode p = h1;
		while(p.next!=null) p =p.next;
		p.next = h2;
		return isCycle(h1);

	}

	public static boolean isCycle(ListNode head){
		if(head == null) return false;
		ListNode slow = head;
		ListNode fast = head;
		while(fast!=null&&fast.next!=null&&slow!=null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow.val == fast.val) return true;
		}
		return false;
	}
	/**
	解法4 ： 如果相交最后的节点一定是相等的
	*/
	public static boolean isConnect4(ListNode h1,ListNode h2){
		if(h1 == null || h2 == null) return false;
		ListNode p = h1;
		ListNode q = h2;
		while(p.next != null) p=p.next;
		while(q.next != null) q=q.next;
		return (p.val == q.val);
	}
	/**
	拓展问题
		1 如果链表可能有环？上面的方法需要怎么调整？
		answer : 解法2依然可以，在装入hashcode的时候判断是否已经存在，如果存在的话，就结束存放即可。

		2 如果我们需要求出两个链表相交的第一个节点呢？
		answer : 先计算出两个节点的长度，然后让比较长的链表先移动差值的次数，然后二者再一起移动，遇到相交的就是第一个相交的节点。
	*/
	public static int getConnectNode(ListNode h1,ListNode h2){
		if(h1 == null || h2 == null) return 0;
		int l1 = 0;
		int l2 = 0;
		ListNode p = h1;
		ListNode q = h2;
		for(;p!=null;p=p.next) l1++;
		for(;q!=null;q=q.next) l2++;
		int m = l1-l2;
		p = h1;
		q = h2;
		if(m>0) while(m-->0) p=p.next;
		else while(m++<0) q=q.next;
		for(;p!=null&&q!=null;p=p.next,q=q.next) if(p.val == q.val) return p.val;
		return 0;
	}
}