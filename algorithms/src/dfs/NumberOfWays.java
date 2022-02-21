package dfs;

public class NumberOfWays {
	int count = 0;
    int delta_x[] = {1,0,-1};
    int mod = (int)10e9 + 7;
    /**
     * @param steps: steps you can move
     * @param arrLen: the length of the array
     * @return: Number of Ways to Stay in the Same Place After Some Steps
     */
    public int numWays(int steps, int arrLen) {
        dfs(0, steps, arrLen);
        return count;
    }

    private void dfs(int idx, int steps, int arrLen) {
        if(idx < 0 || idx > arrLen - 1 || idx > steps) {
            return;
        }

        if(steps == 0) {
        	
        	if(idx == 0) {
        		count++;
        		if(count >= mod)
        			count %= mod;
        	}
            return;
        }

        for(int i = 0; i < 3; i++) {

            dfs(idx + delta_x[i], steps - 1, arrLen);
            
        }
    }
    
    public static void main(String[] args) {
    	NumberOfWays so = new NumberOfWays();
    	int i = so.numWays(4, 5);
    	System.out.println(i);
	}
}
