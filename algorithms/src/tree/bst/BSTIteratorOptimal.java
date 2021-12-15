package tree.bst;

import java.util.Stack;

import tree.TreeNode;

public class  {
    Stack<TreeNode> stack = new Stack<>();
    /**
    * @param root: The root of binary tree.
    */
    public BSTIteratorOptimal(TreeNode root) {
        // do intialization if necessary
        findLeftLast(root);

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
        TreeNode node = stack.pop();
        if(node.right != null) {
            findMostLeft(node.right);
        }
        return node;
    }
}
