package datastructure.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxUnion {

	UnionFindForString uf = new UnionFindForString();

	/**
	 * @param ListA: The relation between ListB's books
	 * @param ListB: The relation between ListA's books
	 * @return: The answer
	 */
	public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
		Map<Integer, String> dig2string = new HashMap<>();
		Map<String, Integer> string2dig = new HashMap<>();

		int n = 0;

		for (int i = 0; i < ListA.length; i++) {
			String aName = ListA[i];
			
			if (!string2dig.containsKey(aName)) {
				dig2string.put(n, aName);
				string2dig.put(aName, n);
				n++;
			}
			String bName = ListB[i];
			if (!string2dig.containsKey(bName)) {
				dig2string.put(n, bName);
				string2dig.put(bName, n);
				n++;
			}
			int x = string2dig.get(aName);
			int y = string2dig.get(bName);
			uf.add(x);
			uf.add(y);
			uf.merge(x, y);
		}

		int max = 0;
		int max_father = -1;
		for (Map.Entry<Integer,Integer> entry : uf.root2size.entrySet()) {
			if ((int) entry.getValue() > max) {
				max = (int) entry.getValue();
				max_father = (int) entry.getKey();
			}
		}

		ArrayList<String> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (uf.find(i) == max_father) {
				res.add(dig2string.get(i));
			}
		}

		return res;
	}

	public static void main(String[] args) {

		String[] ListA = { "abc", "abc", "abc" };
		String[] ListB = { "bcd", "acd", "def" };

		MaxUnion mu = new MaxUnion();

		List<String> res = mu.maximumAssociationSet(ListA, ListB);

		System.out.println(res);
	}
}

class UnionFindForString {
	Map<Integer, Integer> father = new HashMap<>();
	Map<Integer, Integer> root2size = new HashMap<>();

	public void add(int x) {
		if (father.containsKey(x)) {
			return;
		}
		father.put(x, null);
	}

	public void merge(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX != rootY) {
			father.put(rootX, rootY);
			root2size.put(rootY, root2size.getOrDefault(rootY, 1) 
					+ root2size.getOrDefault(rootX, 1));
		}
	}

	public int find(int x) {
		int current = x;

		while (father.get(current) != null) {
			current = father.get(current);
		}

		// 路径压缩
		while (x != current) {
			int origFather = father.get(x);
			father.put(x, current);
			x = origFather;
		}

		return current;
	}

	public boolean isConnected(int x, int y) {
		return find(x) == find(y);
	}
}
