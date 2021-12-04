package datastructure.trie;

public class MaxXorSubarray {
	public int maxXorSubarray(int[] nums) {
		// write code here

		NumTrieNode root = new NumTrieNode();
		root.insert(0);

		int xor_pre = 0;
		int max_xor = 0;
		for (int num : nums) {
			xor_pre ^= num;
			root.insert(xor_pre);
			int temp = root.getNearestNum(xor_pre);
			max_xor = Math.max(max_xor, temp ^ xor_pre);
		}

		return max_xor;
	}
}

class NumTrieNode {
	boolean isNum;
	int num;
	NumTrieNode[] children;

	public NumTrieNode() {
		children = new NumTrieNode[2];
		isNum = false;
		num = 0;
	}

	public void insert(int num) {
		NumTrieNode node = this;
		
		for (int i = 30; i >= 0; i--) {
			int bit = num >> i & 1;
			if (node.children[bit] == null) {
				node.children[bit] = new NumTrieNode();
			}
			node = node.children[bit];
		}
		node.isNum = true;
		node.num = num;
	}

	public int getNearestNum(int num) {
		NumTrieNode node = this;
		for (int i = 30; i >= 0; i--) {
			int bit = num >> i & 1;
			if (node.children[1 - bit] != null) {
				node = node.children[1 - bit];
			} else {
				node = node.children[bit];
			}
		}
		return node.num;
	}

}
