package tree.bst;

import java.util.Stack;

import tree.TreeNode;

public class InorderSuccessorInBST {
    //iterator
    public TreeNode inorderSuccessor_it(TreeNode root, TreeNode p) {
        if(root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();

        while(root != null) {
            stack.push(root);
            root = root.left;
            if(root == p && !stack.isEmpty()) {
                return stack.peek();
            }
        }

        while(!stack.isEmpty()) {
            TreeNode node =  stack.pop();
            

            if(node.right != null) {
                
                TreeNode tmp = node.right;
                while(tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.left;
                }
            } 
            if(node == p && !stack.isEmpty()) {
                return stack.peek();
            }
            
        }
            
        return null;
    }
    
    //recrusive
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p){
        if(root == null || p == null) {
            return null;
        }

        if(p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null? root: left;
        }
    }
}
