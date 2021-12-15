package tree.bst;

import java.util.Stack;

import tree.TreeNode;

public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    /**
    * @param root: The root of binary tree.
    */
    public BSTIterator(TreeNode root) {
        // do intialization if necessary
        findMostLeft(root);

    }


    private void findMostLeft(TreeNode root){
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    

    /**
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode res = stack.peek();
        TreeNode node = res;

        if(node.right != null) {
            findMostLeft(node.right);
        } else {
            node = stack.pop();
            while(!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
        }

        return res;

    }
}
