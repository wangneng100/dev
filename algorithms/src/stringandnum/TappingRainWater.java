package stringandnum;

public class TappingRainWater {
	public static int trap(int[] height) {
        if (height.length <= 1){
            return 0;
        }
        
        int left = 0;
        while (left < height.length - 1 ) {
            if (height[left] != 0){
                break;
            }
            left++;
        }
        
        int right = left + 1;
        int res = 0;
        int black = height[left];
        
        
        
        while (right < height.length) {
            int leftValue = height[left];
            int rightValue = height[right];
            
            if (rightValue >= leftValue) {
                res += (right - left) * leftValue - black;
                black = rightValue;
                left = right++;
                continue;
            } 
            
            black += rightValue;
            right++;
        }
        
        return res;
        
    }
	
	public static void main(String[] args) {
		int[] test = {0,1,0,2,1,0,1,3,2,1,2,1};
		
		System.out.println(trap(test));
	}
}
