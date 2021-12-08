package tree;

public class LongestConsecutive {
    public int longestConsecutive(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return helper(root, null, 0);
    }

    private int helper(TreeNode node, TreeNode parent, int longestWithOutRoot) {
        int res = longestWithOutRoot;
        if(node == null) {
            return 0;
        }

        if(parent != null && parent.val == node.val - 1) {
            res = longestWithOutRoot + 1;
        } else {
            res = 1;
        }
        
        // return res;
        int left = helper(node.left, node, res);
        int right = helper(node.right, node, res);
        return Math.max(res, Math.max(left, right));
    }
}
