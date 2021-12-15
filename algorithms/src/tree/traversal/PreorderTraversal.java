package tree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.TreeNode;

public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null) {
                stack.push(node.right);
            }

            if(node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
        
    }

    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal_recursive(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        preorderTraversal(root, res);

        return res;
        

    }

    private void preorderTraversal(TreeNode root, List<Integer> res) {
        if(root == null) {
            return;
        }

        res.add(root.val);

        preorderTraversal(root.left, res);
        preorderTraversal(root.right, res);
    }
}
