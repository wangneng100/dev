package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor_recrusive(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        
        if(root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right !=  null) {
            return root;
        }
        
        if(left != null) {
            return left;
        }
        
        if(right  != null) {
            return right;
        }
        
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

    // 给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。
    // 两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。
    // 返回 null 如果两个节点在这棵树上不存在最近公共祖先的话。
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        Result res = helper(root, A, B);
        if(res == null) {
            return null;
        }
        return res.isAExist&& res.isBExist ? res.lca: null;
    }
    
    public Result helper(TreeNode root, TreeNode A, TreeNode B) {
        Result res = new Result();
        if(root ==  null) {
            return res;
        }

        
        Result leftRes = helper(root.left, A, B);
        Result rightRes = helper(root.right, A, B);

        res.isAExist = leftRes.isAExist || rightRes.isAExist || root == A;
        res.isBExist = leftRes.isBExist || rightRes.isBExist || root == B;
        
        if(root == A || root == B) {
            res.lca = root;
            return res;
        }
        
        if(leftRes.lca != null && rightRes.lca != null) {
            res.lca = root;
        
        } else if(leftRes.lca != null) {
            res.lca = leftRes.lca;
        } else if(rightRes.lca != null) {
            res.lca = rightRes.lca;
        }

        return res;

    }

    class Result{
        boolean isAExist;
        boolean isBExist;
        TreeNode lca;
    }
    
}


