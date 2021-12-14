package tree;

import java.util.ArrayList;
import java.util.List;

public class FlatternTree {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten1(TreeNode root) {
        List<TreeNode> nodes  = new ArrayList<>();
        getPreOrderList(root, nodes);

        for(int i = 0; i < nodes.size()-1; i++) {
            nodes.get(i).left = null;
            nodes.get(i).right = nodes.get(i+1);
        }
    }


    private void getPreOrderList(TreeNode root, List<TreeNode> nodes){
        if(root == null) {
            return;
        }

        nodes.add(root);
        getPreOrderList(root.left, nodes);
        getPreOrderList(root.right, nodes);
    }

//////////////////////////////////////////////
    public void flatten(TreeNode root) {
        flatternAndReturnLastNode(root);
    }
    private TreeNode flatternAndReturnLastNode(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode leftLast = flatternAndReturnLastNode(root.left);
        TreeNode rightLast = flatternAndReturnLastNode(root.right);

        if(root.left != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if(rightLast != null) {
            return rightLast;
        } else if(leftLast != null){
            return leftLast;
        }

        return root;

    }
}
