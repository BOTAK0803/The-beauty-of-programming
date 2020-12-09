class Test{
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int x){val = x;}
	}
	public static void main(String[] args) {
		
	}
	// 从无头节点单链表中删除节点
	public void deleteListNode(ListNode node){
		if(node == null) return;
		node.val = node.next.val;
		node.next = node.next.next;
	}
	// 翻转链表
	public static ListNode reverseList(ListNode head){
		if(head == null ||  head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = head;
        ListNode cur = head.next;
        dummy.next = null;
        while(pre != null){
            pre.next = dummy.next;
            dummy.next = pre;
            pre = cur;
            if(cur != null)cur=cur.next;
        }
        return dummy.next;
	}

}

