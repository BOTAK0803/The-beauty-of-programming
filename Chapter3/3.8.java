// 3.8 求二叉树中节点的最大距离
class Test{
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(){};
		TreeNode(int val){this.val = val;}
	}
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		TreeNode t8 = new TreeNode(8);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.right = t6;
		t5.left = t7;
		t5.right = t8;
		System.out.println(maxDistance(t1));
		
	}
	public static int depth(TreeNode root){
		if(root == null) return 0;
		return Math.max(depth(root.left),depth(root.right)) + 1;
	}
	public static int maxDistance(TreeNode root){
		if(root == null) return 0;
		return Math.max(Math.max(maxDistance(root.right),maxDistance(root.left)),depth(root) + depth(root));
	}
}