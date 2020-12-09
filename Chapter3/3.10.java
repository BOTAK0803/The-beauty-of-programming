// 3.10 分层遍历二叉树
import java.util.*;
class Test{
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(){};
		TreeNode(int val){this.val = val;}
	}
	public static void main(String[] args) {
		
		/**
		基本问题：
			1 : 
			2 :
		*/
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
		List<List<Integer>> res = levelOrderBottom(t1);
		List<List<Integer>> res2 = levelOrder(t1);
		System.out.println(res);
		System.out.println(res2);
		
	}
	/**
	基本问题
	*/
	public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        
        return ret;
    }

	/**
	拓展问题：
	*/
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        // myidea: 层次遍历么，很简单就是采用队列的方式进行就好了
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 如果root为空的话
        if(root == null) return res;
        int flag = depth(root)-1;
        for(int i = 0;i<=flag;i++) res.add(new ArrayList<Integer>());
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i<size;i++){
                TreeNode tmp = queue.poll();
                res.get(flag).add(tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
            }
            flag--;
        }
        return res;

    }
    // 返回一个树的最大深度
    public static int depth(TreeNode root){
        if(root == null) return 0;
        return Math.max(depth(root.left),depth(root.right))+1;
    }
}