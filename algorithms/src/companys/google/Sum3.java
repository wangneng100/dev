package companys.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum3 {

	public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            if (i == 0 || nums[i - 1] != nums[i]) {
                int lo = i+1, hi = nums.length-1;
        
                while(lo < hi) {
                    int sum = nums[lo] + nums[hi];
                    int target = -1*nums[i];
                    if(sum == target){
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        lo++;
                        hi--;
                        while (lo < hi && nums[lo] == nums[lo - 1])
                            ++lo;
                    } else if(sum < target) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
        
    }
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1,0,1,2,-1,-4};
		System.out.println(threeSum(nums));
		System.out.println((int)('1'-'0'));
	}

}
