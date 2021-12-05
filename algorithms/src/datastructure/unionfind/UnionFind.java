package datastructure.unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFind{
    Map<Integer, Integer> father = new HashMap<>();
    boolean hasCycle = false;
    int numOfEdge = 0;

    public void add(int x) {
        if(father.containsKey(x)) {
            return;
        }
        father.put(x, null);
    }

    public void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            father.put(rootX, rootY);
            
        } else {
        	hasCycle = true;
        }
        numOfEdge++;
    }

    public int find(int x) {
        int current = x;

        while(father.get(current) != null) {
            current = father.get(current);
        }

        //路径压缩
        while(x !=  current) {
            int origFather = father.get(x);
            father.put(x, current);
            x = origFather;
        }

        return current;
    }
    
    public boolean isConnected(int x, int y){
        return find(x)  == find(y);
    }
}
