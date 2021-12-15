package tree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.TreeNode;

public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res =  new ArrayList<>();

        recursive(root, res);
        iterator(root, res);
        return res;
    }

    private void recursive(TreeNode root, List<Integer> res) {
        if(root == null) {
            return;
        }

        recursive(root.left, res);
        res.add(root.val);
        recursive(root.right, res);
    }

    private void iterator(TreeNode root, List<Integer> res){
        if(root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();

        root = goToLastLeft(root, stack);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) {
                goToLastLeft(curr.right, stack);
            }
        }
        
    }

    private TreeNode goToLastLeft(TreeNode root, Deque<TreeNode> stack){
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        return root;
    }

    private void iterator1(TreeNode root, List<Integer> res){
        if(root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        
    }

}
