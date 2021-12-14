package bfs;

public class PostOffice {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int shortestDistance(int[][] grid) {
        // write your code here
        return 0;
    }
    
    
    boolean isValid(int x, int y, int[][] grid) {
    	int n = grid.length, m = grid[0].length;
    	if(x >= n || x < 0 || y >= m || y < 0) {
    		return false;
    	}
    	
    	return grid[x][y] == GRIDTYPE.EMPTY || grid[x][y] == GRIDTYPE.HOUSE;
    }

}

interface GRIDTYPE{
	int EMPTY = 0;
	int HOUSE = 1;
}
