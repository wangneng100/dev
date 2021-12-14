package stringandarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 * @author nwang
 *
 */
public class SubarraySumEqualsK {
	public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int len = nums.length;
        
        Map<Integer,Integer> sum2count = new HashMap<>();
        sum2count.put(0, 1);
        
        int res = 0;
        for (int i = 0; i < len; i++) {
        	sum = sum + nums[i];
        	if(sum2count.containsKey(sum - k)) {
        		res += sum2count.get(sum - k);
        	}
        	sum2count.put(sum, sum2count.getOrDefault(sum, 0) + 1);
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		SubarraySumEqualsK sk = new SubarraySumEqualsK();
		System.out.println(sk.subarraySum(new int[] {1,1,0,2}, 2));
	}
}
