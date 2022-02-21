package datastructure.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public int longestConsecutive2(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        
        int max = 1;
        Arrays.sort(nums);
        int count = 1;
        
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1] - 1) {
                count++;
            }else if(nums[i] != nums[i+1]) {
                // System.out.println("i:" + i + " count" + count);
                max = Math.max(max, count);
                count = 1; 
            }
        }
        
        max = Math.max(max, count);
        
        return max;
    }
}
