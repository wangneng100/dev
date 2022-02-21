package twopointers.opposite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
https://www.lintcode.com/problem/57/description
Description
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Example 2:

Input:

numbers = [-1,0,1,2,-1,-4]
Output:

[[-1, 0, 1],[-1, -1, 2]]
*/
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] numbers) {
        Arrays.sort(numbers);

        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < numbers.length - 2; i++) {
            if(i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            helper(res, numbers, i);
        }

        return res;
    }

    private void helper(List<List<Integer>> res, int[] numbers, int idx){
        int target = 0 - numbers[idx];

        int left = idx+1; 
        int right = numbers.length - 1;

        while(left < right) {

            int sum = numbers[left] + numbers[right];
            if(sum == target) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(numbers[idx]);
                tmp.add(numbers[left]);
                tmp.add(numbers[right]);
                res.add(tmp);

                left++;
                right--;
                // skip duplicate pairs with the same left
                while(left < right && numbers[left] == numbers[left - 1]) {
                    left++;
                }
                // skip duplicate pairs with the same right
                while(left < right && numbers[right] == numbers[right + 1]) {
                    right--;
                }
            }
            else if( sum < target) {
                left++; 
            } else {
                right--;
            }   
        }
    }
}
