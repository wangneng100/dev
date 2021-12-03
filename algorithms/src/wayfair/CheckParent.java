package wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class CheckParent {

	
	/**
	 * 			1
	 * 		2		3
	 * 	  4   5		6
	 * @param args
	 */
	public static void main(String[] args) {
		Vector<int[]> data = new Vector<>();
		
		
		HashMap<Integer, Integer> node2parentCount = new HashMap<>();
		
		data.add(new int[]{1,2});
		data.add(new int[]{1,3});
		data.add(new int[]{2,4});
		data.add(new int[]{2,5});
		data.add(new int[]{0,4});
		
		for(int[] pair: data) {
			int parent = pair[0];
			if(!node2parentCount.containsKey(parent)) {
				node2parentCount.put(parent, 0);
			}
			
			int child = pair[1];
			int count = node2parentCount.getOrDefault(child, 0);
			node2parentCount.put(child, count+1);
		}
		
		
		ArrayList<Integer> noParent = new ArrayList<>();
		ArrayList<Integer> oneParent = new ArrayList<>();
		for(int node: node2parentCount.keySet()) {
			int count = node2parentCount.get(node);
			if(count == 0) {
				noParent.add(node);
			}
			else if(count == 1) {
				oneParent.add(node);
			}
		}
		
		int[] onePA = noParent.stream().mapToInt(i->i).toArray();
		System.out.println(noParent);
		System.out.println(oneParent);
		
	}
	
}
