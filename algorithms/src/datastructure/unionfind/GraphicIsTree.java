package datastructure.unionfind;

public class GraphicIsTree {

    UnionFind uf = new UnionFind();
    
    /**
     * @param a: the node a
     * @param b: the node b
     * @return: nothing
     */
    public void addEdge(int a, int b) {
        uf.add(a);
        uf.add(b);
        uf.merge(a, b);
    }

    /**
     * @return: check whether these edges make up a valid tree
     */
    public boolean isValidTree() {
        if(uf.numOfEdge != uf.father.size() - 1) {
            return false;
        }
        return !uf.hasCycle;
    }
    
    public static void main(String[] args) {
    	GraphicIsTree isTree = new GraphicIsTree();
    	isTree.addEdge(1, 2);
    	System.out.println(isTree.isValidTree());
    	
    	isTree.addEdge(3, 4);
    	System.out.println(isTree.isValidTree());
    	
    	isTree.addEdge(4, 5);
    	System.out.println(isTree.isValidTree());
    	
    	isTree.addEdge(3, 5);
    	System.out.println(isTree.isValidTree());
    	
	}
}
