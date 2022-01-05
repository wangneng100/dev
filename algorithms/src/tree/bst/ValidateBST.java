package tree.bst;

import tree.TreeNode;

public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return helper(root).isBST;
    }

    private Result helper(TreeNode root){
        if(root == null) {
            return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }

        Result left = helper(root.left);
        Result right = helper(root.right);

        if(!left.isBST || !right.isBST) {
            return new Result(0, 0, false);
        }

        if (root.left != null && left.max >= root.val || 
              root.right != null && right.min <= root.val) {
            return new Result(0, 0, false);
        }

        return new Result(Math.min(root.val,left.min), Math.max(root.val, right.max), true);
    }
}

class Result{
    int min;
    int max;
    boolean isBST;

    public Result(int min, int max, boolean isBST){
        this.min = min;
        this.max = max;
        this.isBST = isBST;
    }
}