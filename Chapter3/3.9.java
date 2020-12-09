// 3.9 重建二叉树
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
		基本问题：根据前序遍历以及中序遍历的结构重构二叉树。

		*/

		int[] preorder = new int[]{3,9,20,15,7};
		int[] inorder = new int[]{9,3,15,20,7};
		System.out.println(buildTree(preorder,inorder).val);
		
	}
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        return help(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode help(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        //递归的第一步：递归终止条件，避免死循环
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        //重建根节点
        TreeNode treeNode = new TreeNode(preorder[pStart]);
        int index = 0;  //index找到根节点在中序遍历的位置
        while (inorder[iStart + index] != preorder[pStart]) {
            index++;
        }
        //重建左子树
        treeNode.left = help(preorder, pStart + 1, pStart + index, inorder, iStart, iStart + index - 1);
        //重建右子树
        treeNode.right = help(preorder, pStart + index + 1, pEnd, inorder, iStart + index + 1, iEnd);
        return treeNode;

    }
	/**
	拓展问题：
		1 如果根据字母不能够确定节点，换句话而言就是，节点上面的字母有可能是相同的。那么，这道题应该怎么来做呢？
			> answer 重构出来的二叉树是唯一的么？如果不是唯一的，如何重构出来所有可能的解呢？
		2 如何判定给定的前序遍历和中序遍历的结果是合理的呢？
			> answer 
		3 如果知道前序遍历和后续遍历的结果，能构建成二叉树么？
			> answer 不能
	*/
}