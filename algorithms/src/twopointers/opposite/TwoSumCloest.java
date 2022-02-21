package twopointers.opposite;

import java.util.Arrays;
/*
https://www.lintcode.com/problem/533
Description
Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.

Return the absolute value of difference between the sum of the two numbers and the target.

Wechat reply 【533】 get the latest requent Interview questions . (wechat id : jiuzhang15)

Example
Example1

Input:  nums = [-1, 2, 1, -4] and target = 4
Output: 1
Explanation:
The minimum difference is 1. (4 - (2 + 1) = 1).
Example2

Input:  nums = [-1, -1, -1, -4] and target = 4
Output: 6
Explanation:
The minimum difference is 6. (4 - (- 1 - 1) = 6).

*/
public class TwoSumCloest {
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        
        Arrays.sort(nums);
        
        int left = 0, right = nums.length - 1;
        int diff = Integer.MAX_VALUE;
        
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                diff = Math.min(diff, target - nums[left] - nums[right]);
                left++;
            } else {
                diff = Math.min(diff, nums[left] + nums[right] - target);
                right--;
            }
        }
        
        return diff;
    }
}
