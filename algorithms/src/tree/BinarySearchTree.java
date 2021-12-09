package tree;

public class BinarySearchTree {
	public TreeNode search(TreeNode root, int val) {
		if (root == null) {
			return null;
		}

		if (root.val == val) {
			return root;
		}

		if (root.val > val) {
			return search(root.left, val);
		} else {
			return search(root.right, val);
		}
	}

	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return node;
		}
		if (root.val > node.val) {
			root.left = insertNode(root.left, node);
		} else {
			root.right = insertNode(root.right, node);
		}
		return root;
	}

	public int getLower(TreeNode node) {
		node = node.left;
		while (node.right != null) {
			node = node.right;
		}
		return node.val;
	}

	public int getHigher(TreeNode node) {
		node = node.right;
		while (node.left != null) {
			node = node.left;
		}
		return node.val;
	}

	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.val > key) {
			root.left = deleteNode(root.left, key);
		} else if (root.val < key) {
			root.right = deleteNode(root.right, key);
		} else {

			if (root.left == null && root.right == null) {
				root = null;
			} else if (root.right != null) {
				root.val = getHigher(root);
				root.right = deleteNode(root.right, root.val);
			} else {
				root.val = getLower(root);
				root.left = deleteNode(root.left, root.val);
			}
		}
		return root;
	}

	public boolean isValidBST(TreeNode root) {
		return helper(root, null, null);
	}

	private boolean helper(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;

		if (max != null && root.val >= max)
			return false;

		if (min != null && root.val <= min)
			return false;

		return helper(root.left, min, root.val) && helper(root.right, root.val, max);
	}

}
