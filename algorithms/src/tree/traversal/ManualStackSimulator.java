package tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.TreeNode;

public class ManualStackSimulator {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<State> stack = new Stack<>();
        stack.push(new State(root, 0));
        List<Integer> values = new ArrayList<>();

        while (!stack.empty()) {
            State now = stack.pop();
            TreeNode node = now.node;
            int count = now.count;

            if (node == null) {
                continue;
            }

            if (count == 0) {
                stack.push(new State(node, 3));
                stack.push(new State(node.right, 0));
                stack.push(new State(node, 2));
                stack.push(new State(node.left, 0));
                stack.push(new State(node, 1));
            }

            if (count == 1) {
                values.add(node.val);
            }
        }

        return values;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<State> stack = new Stack<>();
        stack.push(new State(root, 0));
        List<Integer> values = new ArrayList<>();

        while (!stack.empty()) {
            State now = stack.pop();
            TreeNode node = now.node;
            int count = now.count;

            if (node == null) {
                continue;
            }

            if (count == 0) {
                stack.push(new State(node, 3));
                stack.push(new State(node.right, 0));
                stack.push(new State(node, 2));
                stack.push(new State(node.left, 0));
                stack.push(new State(node, 1));
            }

            if (count == 2) {
                values.add(node.val);
            }
        }

        return values;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<State> stack = new Stack<>();
        stack.push(new State(root, 0));
        List<Integer> values = new ArrayList<>();

        while (!stack.empty()) {
            State now = stack.pop();
            TreeNode node = now.node;
            int count = now.count;

            if (node == null) {
                continue;
            }

            if (count == 0) {
                stack.push(new State(node, 3));
                stack.push(new State(node.right, 0));
                stack.push(new State(node, 2));
                stack.push(new State(node.left, 0));
                stack.push(new State(node, 1));
            }

            if (count == 3) {
                values.add(node.val);
            }
        }

        return values;
    }

}

class State {
    TreeNode node;
    int count;

    public State(TreeNode node, int count) {
        this.node = node;
        this.count = count;
    }
}
