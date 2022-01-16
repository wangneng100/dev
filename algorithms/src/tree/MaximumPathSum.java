package tree;

public class MaximumPathSum {
    public int maxPathSum(TreeNode root) {
        return helper(root).maxPath;
    }

    private Result helper(TreeNode root){
        if(root == null) {
            return new Result(0, Integer.MIN_VALUE);
        }

        Result left = helper(root.left);
        Result right = helper(root.right);

        int singlePath = Math.max(left.singlePath + root.val, root.val);
        singlePath = Math.max(right.singlePath + root.val, singlePath);

        // int leftPath = left.singlePath > 0 ? left.singlePath + root.val : root.val;
        // int rightPath = right.singlePath > 0 ? right.singlePath + root.val : root.val;
        // int singlePath = Math.max(leftPath, rightPath);

        int max = Math.max(left.singlePath + root.val, root.val);
        max = Math.max(right.singlePath + max, max);
        max = Math.max(max, Math.max(left.maxPath, right.maxPath));

        return new Result(singlePath, max);
    }

    class Result{
        // singlePath means the max path from node to other child
        int singlePath;
    
        // maxPath means max path 
        int maxPath;
    
        public Result(int single, int max){
            singlePath = single;
            maxPath = max;
        }
    }
}



