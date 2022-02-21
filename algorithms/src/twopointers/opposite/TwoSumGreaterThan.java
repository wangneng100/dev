package twopointers.opposite;

import java.util.Arrays;

/*
https://www.lintcode.com/problem/443/description
Description
Given an array of integers, find how many pairs in the array such that their sum is bigger than a specific target number. Please return the number of pairs.

Wechat reply 【443】 get the latest requent Interview questions . (wechat id : jiuzhang15)

Example
Example 1:

Input: [2, 7, 11, 15], target = 24
Output: 1
Explanation: 11 + 15 is the only pair.
Example 2:

Input: [1, 1, 1, 1], target = 1
Output: 6
*/
public class TwoSumGreaterThan {
    public int twoSum2(int[] nums, int target) {
        int n = nums.length; 
        Arrays.sort(nums);
        int result = 0, l = 0, r = n - 1;
        while (l < r) {
            //若l指向元素与r指向元素的和不大于target
            if (nums[l] + nums[r] <= target) {
                l++;
            //否则，nums[l]可以与nums[l + 1 : r + 1]这r - l个元素配对    
            } else {
                result += r - l;
                r--;
            }
        }

        return result;
    }
}
